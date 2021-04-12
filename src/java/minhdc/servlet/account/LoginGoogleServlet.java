/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.servlet.account;

import minhdc.utils.GooglePojo;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhdc.tblUser.TblUserDAO;
import minhdc.tblUser.TblUserDTO;
import minhdc.utils.GoogleUtils;

/**
 *
 * @author MONS
 */
@WebServlet(name = "LoginGoogleServlet", urlPatterns = {"/LoginGoogleServlet"})
public class LoginGoogleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final String HOME_PAGE = "home-page.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String code = request.getParameter("code");
        PrintWriter out = response.getWriter();

        String url = HOME_PAGE;
        try {

            if (code == null || code.isEmpty()) {
                RequestDispatcher dis = request.getRequestDispatcher("signin-page.jsp");
                dis.forward(request, response);
            } else {
                String accessToken = GoogleUtils.getToken(code);
                GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);

                TblUserDAO dao = new TblUserDAO();
                TblUserDTO dto = dao.checkLoginGoogle(googlePojo.getId(), googlePojo.getEmail());

                if (dto != null) {
                    url = HOME_PAGE;
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", dto);
                } else {
                    if (dao.registerAccount(googlePojo.getId(), googlePojo.getEmail())) {
                        TblUserDTO newUser = dao.checkLoginGoogle(googlePojo.getId(), googlePojo.getEmail());
                        url = HOME_PAGE;
                        HttpSession session = request.getSession();
                        session.setAttribute("USER", newUser);
                    }
                }

            }
        } catch (SQLException e) {
            log("LoginGoogleServlet_SQLex: " + e.getMessage());
        } catch (NamingException e) {
            log("LoginGoogleServlet_NamingEx: " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            log("LoginGoogleServlet_NoSuchAlgorithmEx: " + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
