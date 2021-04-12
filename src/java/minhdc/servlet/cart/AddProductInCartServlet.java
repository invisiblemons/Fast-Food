/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.servlet.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhdc.cart.CartObject;
import minhdc.tblProduct.TblProductDTO;
import minhdc.tblUser.TblUserDTO;

/**
 *
 * @author MONS
 */
@WebServlet(name = "AddProductInCartServlet", urlPatterns = {"/AddProductInCartServlet"})
public class AddProductInCartServlet extends HttpServlet {

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
        String productName = request.getParameter("productName");
        String productPrice = request.getParameter("productPrice");
        String productCategory = request.getParameter("productCategory");
        String image = request.getParameter("image");
        String quantity = request.getParameter("quantity");
        int quanCart = 1;

        HttpSession session = request.getSession();
        TblUserDTO user = (TblUserDTO) session.getAttribute("USER");
        String searchValue = request.getParameter("searchValueName");
        String minValue = request.getParameter("minPrice");
        String maxValue = request.getParameter("maxPrice");
        String categorySearch = request.getParameter("categorySearchValue");
        String btAction = request.getParameter("btActionSearch");
        if (null==btAction||"".equals(btAction)) {
            btAction = "loadItems";
        }
        CartObject cart = (CartObject) session.getAttribute("CARTCUSTOMER");
            if (cart == null) {
                cart = new CartObject();
            }
            java.util.Date dateUtil = new java.util.Date();
            java.sql.Date buyDate = new java.sql.Date(dateUtil.getTime());
            
        try {
            TblProductDTO dto = new TblProductDTO(productID, productName, image, Float.parseFloat(productPrice), buyDate, productCategory, user.getUsername(), Integer.parseInt(quantity), quanCart);
            cart.addProductToCart(dto);
            session.setAttribute("CARTCUSTOMER", cart);
        } finally {
            String urlRewriting = "DispatcherServlet?btAction=" + btAction
                            + "&searchValueName=" + searchValue + "&minPrice=" + minValue + "&maxPrice=" + maxValue + "&categorySearchValue=" + categorySearch ;
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
