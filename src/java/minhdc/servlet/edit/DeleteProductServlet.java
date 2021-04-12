/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.servlet.edit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhdc.tblProduct.TblProductDAO;
import minhdc.tblUser.TblUserDTO;

/**
 *
 * @author MONS
 */
@WebServlet(name = "DeleteProductServlet", urlPatterns = {"/DeleteProductServlet"})
public class DeleteProductServlet extends HttpServlet {
    private final String ERROR_PAGE = "error.jsp";
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
        PrintWriter out = response.getWriter();
        String[] chkCheckBoxRemove = request.getParameterValues("chkRemove");
        HttpSession session = request.getSession();
        TblUserDTO user = (TblUserDTO) session.getAttribute("USER");
        String lastUserDelete = user.getUsername();
        String searchValue = request.getParameter("searchValueName");
        String minValue = request.getParameter("minPrice");
        String maxValue = request.getParameter("maxPrice");
        String categorySearch = request.getParameter("categorySearchValue");
        String btAction = request.getParameter("btActionSearch");
        if (null==btAction||"".equals(btAction)) {
            btAction = "loadItems";
        }
        
        String urlRewriting = ERROR_PAGE;
        try {
            if (chkCheckBoxRemove != null) {
                TblProductDAO dao = new TblProductDAO();
                java.util.Date date = new java.util.Date();
                Date lastUpdate = new Date(date.getTime());
                boolean result = false;
                for (String productID : chkCheckBoxRemove) {
                    result = dao.deleteProduct(productID, lastUserDelete, lastUpdate, false);
                    if (result) {
                        urlRewriting = "DispatcherServlet?btAction="+btAction
                        +"&searchValueName="+searchValue+ "&minPrice="+minValue+"&maxPrice="+maxValue+"&categorySearchValue="+categorySearch;
                    }
                }
            } else {
                String msg = "Choose products to delete!";
                urlRewriting = "DispatcherServlet?btAction="+btAction
                        +"&searchValueName="+searchValue+ "&minPrice="+minValue+"&maxPrice="+maxValue+"&categorySearchValue="+categorySearch+"&deleteError="+msg;
               
            }
                     
                
        } catch (SQLException ex) {
            log("DeleteProductsServlet_SQLEx: " + ex.getMessage());
        } catch (NamingException ex) {
            log("DeleteProductsServlet_Naming: " + ex.getMessage());
        } finally {
            response.sendRedirect(urlRewriting);
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
