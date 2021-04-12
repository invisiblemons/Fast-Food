/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.servlet.orderHistory;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhdc.tblOrder.TblOrderDAO;
import minhdc.tblOrder.TblOrderDTO;
import minhdc.tblOrderDetailDAO.TblOrderDetailDAO;
import minhdc.tblOrderDetailDAO.TblOrderDetailDTO;
import minhdc.tblProduct.TblProductDAO;
import minhdc.tblUser.TblUserDAO;
import minhdc.tblUser.TblUserDTO;

/**
 *
 * @author MONS
 */
@WebServlet(name = "OrderHistoryServlet", urlPatterns = {"/OrderHistoryServlet"})
public class OrderHistoryServlet extends HttpServlet {

    private final String HISTORY_PAGE = "history-page.jsp";

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

        TblOrderDAO orderDAO = new TblOrderDAO();
        TblOrderDetailDAO DAO = new TblOrderDetailDAO();
        TblProductDAO dao = new TblProductDAO();
        TblUserDAO Dao = new TblUserDAO();
        HttpSession session = request.getSession();
        TblUserDTO user = (TblUserDTO) session.getAttribute("USER");

        session.getAttribute("LISTORDERS");
        session.getAttribute("LISTORDERSDETAIL");
        try {
            ArrayList<TblOrderDTO> listOrders = orderDAO.loadOrders(user.getUsername());
            Map<Integer, ArrayList<TblOrderDetailDTO>> orderDetailMap = new HashMap<>();
            for (TblOrderDTO order : listOrders) {
                ArrayList<TblOrderDetailDTO> listOrdersDetail = DAO.loadOrderDetail(order.getOrderID());
                orderDetailMap.put(order.getOrderID(), listOrdersDetail);
            }
            session.setAttribute("LISTORDERS", listOrders);
            session.setAttribute("LISTORDERSDETAIL", orderDetailMap);
        } catch (SQLException ex) {
            log("OrderHistoryServlet_SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("OrderHistoryServlet_Naming " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(HISTORY_PAGE);
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
