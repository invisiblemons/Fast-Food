/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.servlet.edit;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "UpdateProductServlet", urlPatterns = {"/UpdateProductServlet"})
public class UpdateProductServlet extends HttpServlet {
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
        String ID = request.getParameter("ID");
        String Name = request.getParameter("Name");
        String Image = request.getParameter("Image");
        String Descrip = request.getParameter("Description");
        String Price = request.getParameter("Price");
        String Category = request.getParameter("Category");
        Category = Category.substring(0, 1);
        String Status = request.getParameter("Status");
        String Quantity = request.getParameter("Quantity");
        
        boolean status = true;
        if(("false").equals(Status))
            status = false;
        
        
        java.util.Date dateUtil = new java.util.Date();
        java.sql.Date lastUpdateDate = new java.sql.Date(dateUtil.getTime());
       
        HttpSession session = request.getSession();
        TblUserDTO user = (TblUserDTO) session.getAttribute("USER");
        String UserUpdate = user.getUsername();
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
            TblProductDAO dao = new TblProductDAO();
            boolean result = dao.UpdateProduct(ID, Name, Image, Descrip, Float.parseFloat(Price),
                    status, Integer.parseInt(Quantity), UserUpdate, lastUpdateDate,Category );
            if(result)
            {
            urlRewriting = "DispatcherServlet?btAction="+btAction
                        +"&searchValueName="+searchValue+ "&minPrice="+minValue+"&maxPrice="+maxValue+"&categorySearchValue="+categorySearch+"&productName="+Name+"&productID="+ID;
            
            }
        } catch (SQLException ex) {
            log("UpdateProductServlet_SQLEx: " + ex.getMessage());
        } catch (NamingException ex) {
            log("UpdateProductServlet_NamingEx: " + ex.getMessage());
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
