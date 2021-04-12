/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.servlet.cart;

import java.io.IOException;
import java.io.PrintWriter;
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
import minhdc.cart.CartObject;
import minhdc.tblProduct.TblProductDAO;

/**
 *
 * @author MONS
 */
@WebServlet(name = "UpdateProductInCartServlet", urlPatterns = {"/UpdateProductInCartServlet"})
public class UpdateProductInCartServlet extends HttpServlet {

    private final String CHECKOUT_PAGE = "checkout-page.jsp";

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
        String productID = request.getParameter("productID");
        String productQuanity = request.getParameter("quanCart");

        String searchValue = request.getParameter("searchValueName");
        String minValue = request.getParameter("minPrice");
        String maxValue = request.getParameter("maxPrice");
        String categorySearch = request.getParameter("categorySearchValue");
        String btAction = request.getParameter("ACTION");

        HttpSession session = request.getSession();
        CartObject cart = (CartObject) session.getAttribute("CARTCUSTOMER");
        TblProductDAO dao = new TblProductDAO();
        try {
            if (Integer.parseInt(productQuanity) == 0) {
                if (cart != null) {

                    if (productID != null) {
                        cart.removeProductFromCart(productID);

                        if (cart.getCartProduct() == null) {
                            session.setAttribute("Cart", null);
                        } else {
                            session.setAttribute("Cart", cart);
                        }
                    }
                }
            } else {

                if (cart != null) {
                    
                    if(dao.getProduct(productID).getQuantity()<Integer.parseInt(productQuanity))
                       request.setAttribute("updateError", true);
                    else if(dao.getProduct(productID).getQuantity()>=Integer.parseInt(productQuanity))
                        cart.getCartProduct().get(productID).setQuancart(Integer.parseInt(productQuanity));
                }
                session.setAttribute("CARTCUSTOMER", cart);
            }
            request.setAttribute("searchValueName", searchValue);
            request.setAttribute("minPrice", minValue);
            request.setAttribute("maxPrice", maxValue);
            request.setAttribute("categorySearchValue", categorySearch);
            request.setAttribute("ACTION", btAction);
        } catch (SQLException ex) {
            log("UpdateProductInCartServlet_SQLEx: " + ex.getMessage());
        } catch (NamingException ex) {
            log("UpdateProductInCartServlet_NamingEx: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(CHECKOUT_PAGE);
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
