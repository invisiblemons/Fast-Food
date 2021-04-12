/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MONS
 */
public class DispatcherServlet extends HttpServlet {
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
        PrintWriter out = response.getWriter();
        String url = HOME_PAGE;
        String searchValue = request.getParameter("searchValueName");
        String minValue = request.getParameter("minPrice");
        String maxValue = request.getParameter("maxPrice");
        String categorySearch = request.getParameter("categorySearchValue");
        String btAction = request.getParameter("ACTION");
        
            
        try {
            request.setAttribute("searchValueName", searchValue);
            request.setAttribute("minPrice", minValue);
            request.setAttribute("maxPrice", maxValue);
            request.setAttribute("categorySearchValue", categorySearch);
            request.setAttribute("ACTION", btAction);
            
            //Dispatcher
            String button = request.getParameter("btAction");
            if ("login".equals(button)) {
                url = "LoginServlet";
            } else if ("loginPage".equals(button)) {
                url = "signin-page.jsp";
            } else if ("logout".equals(button)) {
                url = "LogOutServlet";
            } else if ("loadItems".equals(button)) {
                url = "LoadItemsServlet";
            } else if ("searchByName".equals(button)) {
                url = "LoadItemsServlet";
            } else if ("searchByPrice".equals(button)) {
                url = "LoadItemsServlet";
            } else if ("searchByCategory".equals(button)) {
                url = "LoadItemsServlet";
            } else if ("deleteProducts".equals(button)) {
                url = "DeleteProductServlet";
            } else if ("updateProduct".equals(button)) {
                url = "UpdateProductServlet";
            } else if ("createNewPoduct".equals(button)) {
                url = "CreateNewPoductServler";
            } else if ("addProductInCart".equals(button)) {
                url = "AddProductInCartServlet";
            } else if ("deleteProductInCart".equals(button)) {
                url = "DeleteProductInCartServlet";
            } else if ("updateProductInCart".equals(button)) {
                url = "UpdateProductInCartServlet";
            } else if ("checkout".equals(button)) {
                url = "CheckOutServlet";
            } else if ("orderHistory".equals(button)) {
                url = "OrderHistoryServlet";
            } else if ("searchNameOrder".equals(button)) {
                url = "SearchNameOrderServlet";
            } else if ("searchDateOrder".equals(button)) {
                url = "SearchDateOrderServlet";
            } else if ("viewCart".equals(button)) {
                url = "ViewCartServlet";
            } else if ("login-google".equals(button)) {
                url = "LoginGoogleServlet";
            }
            
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
