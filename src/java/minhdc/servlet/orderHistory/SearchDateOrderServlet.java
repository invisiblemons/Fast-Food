/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.servlet.orderHistory;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
@WebServlet(name = "SearchDateOrderServlet", urlPatterns = {"/SearchDateOrderServlet"})
public class SearchDateOrderServlet extends HttpServlet {

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
        String inDate = request.getParameter("searchDate_Order");
        SimpleDateFormat inSDF = new SimpleDateFormat("mm/dd/yyyy");
        SimpleDateFormat outSDF = new SimpleDateFormat("yyyy-mm-dd");

        String url = HISTORY_PAGE;

        TblOrderDAO orderDAO = new TblOrderDAO();
        TblOrderDetailDAO DAO = new TblOrderDetailDAO();
        TblProductDAO dao = new TblProductDAO();
        TblUserDAO Dao = new TblUserDAO();
        HttpSession session = request.getSession();
        TblUserDTO user = (TblUserDTO) session.getAttribute("USER");

        session.getAttribute("LISTORDERS");
        session.getAttribute("LISTORDERSDETAIL");
        try {
            ArrayList<TblOrderDTO> listOrderss = orderDAO.loadOrders(user.getUsername());
            Map<Integer, ArrayList<TblOrderDetailDTO>> orderDetailMap = new HashMap<>();
            for (TblOrderDTO order : listOrderss) {
                ArrayList<TblOrderDetailDTO> listOrdersDetail = DAO.loadOrderDetail(order.getOrderID());
                orderDetailMap.put(order.getOrderID(), listOrdersDetail);
            }
            session.setAttribute("LISTORDERS", listOrderss);
            session.setAttribute("LISTORDERSDETAIL", orderDetailMap);

            //Search
            Map<Integer, ArrayList<TblOrderDetailDTO>> listDetail = new HashMap<>();
            ArrayList<Integer> orderListSearch = new ArrayList<>();
            ArrayList<TblOrderDTO> searchList = new ArrayList<>();

            ArrayList<TblOrderDTO> listOrders = (ArrayList<TblOrderDTO>) session.getAttribute("LISTORDERS");
            listDetail = (Map<Integer, ArrayList<TblOrderDetailDTO>>) session.getAttribute("LISTORDERSDETAIL");

            if (null == inDate || "".equals(inDate.trim())) {
                url = "OrderHistoryServlet";
            } else {
                //change Date
                String outDate = "";
                if (inDate != null) {
                    Date date = inSDF.parse(inDate);
                    outDate = outSDF.format(date);
                }
                for (TblOrderDTO listOrder : listOrders) {
                    if (listOrder.getDateOrder().toString().equals(outDate)) {
                        orderListSearch.add(listOrder.getOrderID());
                    }
                }
                for (Integer OrderSearch : orderListSearch) {
                    for (TblOrderDTO Order : listOrders) {
                        if (Order.getOrderID() == OrderSearch) {
                            searchList.add(Order);
                        }
                    }
                }
                session.setAttribute("LISTORDERS", searchList);
                request.setAttribute("searchDate", inDate);
            }
        } catch (SQLException ex) {
            log("SearchDateOrderServlet_SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("SearchDateOrderServlet_Naming " + ex.getMessage());
        } catch (ParseException ex) {
            log("SearchDateOrderServlet_Parse " + ex.getMessage());
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
