/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.servlet.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhdc.cart.CartObject;
import minhdc.tblOrder.TblOrderDAO;
import minhdc.tblOrderDetailDAO.TblOrderDetailDAO;
import minhdc.tblProduct.TblProductDAO;
import minhdc.tblProduct.TblProductDTO;
import minhdc.tblUser.TblUserDTO;

/**
 *
 * @author MONS
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

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
        String totalPrice = request.getParameter("totalPrice");
        float totalprice = Float.parseFloat(totalPrice);
        String paymentMethod = request.getParameter("paymentMethod");

        HttpSession session = request.getSession();
        TblUserDTO user = (TblUserDTO) session.getAttribute("USER");

        String searchValue = request.getParameter("searchValueName");
        String minValue = request.getParameter("minPrice");
        String maxValue = request.getParameter("maxPrice");
        String categorySearch = request.getParameter("categorySearchValue");
        String btAction = request.getParameter("ACTION");

        try {
            CartObject cart = (CartObject) session.getAttribute("CARTCUSTOMER");
            if (cart == null) {
                cart = new CartObject();
            }
            java.util.Date dateUtil = new java.util.Date();
            java.sql.Date buyDate = new java.sql.Date(dateUtil.getTime());
            TblOrderDAO dao = new TblOrderDAO();
            if (null != cart.getCartProduct()) {
                if (cart.getCartProduct().size() != 0) {
                    if (dao.insertRecord(user.getUsername(), paymentMethod, buyDate, totalprice)) {
                        int idOrder = dao.getId();

                        for (TblProductDTO value : cart.getCartProduct().values()) {
                            TblOrderDetailDAO Dao = new TblOrderDetailDAO();
                            Dao.insertRecord(idOrder, value.getProductID(), value.getQuancart(), value.getQuancart() * value.getProductPrice());
                            TblProductDAO product = new TblProductDAO();
                            int count = product.getProduct(value.getProductID()).getQuantity() - value.getQuancart();
                            product.updateCount(value.getProductID(), value.getQuancart() + product.getProduct(value.getProductID()).getCount());
                            if (count <= 0) {
                                product.updateQuantity(value.getProductID(), 0);
                                product.updateStatus(value.getProductID(), false);
                            } else {
                                product.updateQuantity(value.getProductID(), count);
                            }
                        }
                        session.removeAttribute("CARTCUSTOMER");
                        request.setAttribute("SUCCESSCHECKOUT", "Successfully check-out!");
                    }
                }
            }
            request.setAttribute("searchValueName", searchValue);
            request.setAttribute("minPrice", minValue);
            request.setAttribute("maxPrice", maxValue);
            request.setAttribute("categorySearchValue", categorySearch);
            request.setAttribute("ACTION", btAction);

        } catch (SQLException e) {
            log("CheckoutServlet_SQLex: " + e.getMessage());
        } catch (NamingException e) {
            log("CheckoutServlet_NamingEx: " + e.getMessage());
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
