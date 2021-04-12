/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.servlet.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhdc.tblCategory.TblCategoryDAO;
import minhdc.tblProduct.TblProductDAO;
import minhdc.tblProduct.TblProductDTO;
import minhdc.tblUser.TblUserDTO;

/**
 *
 * @author MONS
 */
@WebServlet(name = "LoadItemsServlet", urlPatterns = {"/LoadItemsServlet"})
public class LoadItemsServlet extends HttpServlet {

    private final String SHOPPING_PAGE = "shopping-page.jsp";

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
        HttpSession session = request.getSession();
        String searchByName = request.getParameter("searchValueName");
        String searchByCategory = request.getParameter("categorySearchValue");
        String minValue = request.getParameter("minPrice");
        String maxValue = request.getParameter("maxPrice");

        String url = SHOPPING_PAGE;

        TblProductDAO dao = new TblProductDAO();
        //chk admin
        boolean isAdmin = false;
        TblUserDTO user = (TblUserDTO) session.getAttribute("USER");
        if (user != null) {
            isAdmin = user.isIsAdmin();
            try {
                //load list
                ArrayList<TblProductDTO> arrListItem = (ArrayList<TblProductDTO>) session.getAttribute("ITEMSLIST");
                if (arrListItem == null) {
                    arrListItem = new ArrayList<>();
                } else {
                    arrListItem.clear();
                }
                arrListItem = dao.loadStore(isAdmin);
                Collections.sort(arrListItem);
                session.setAttribute("ITEMSLIST", arrListItem);
                //get list category
                TblCategoryDAO categoryDao = new TblCategoryDAO();
                ArrayList<String> categoryList = categoryDao.loadCategory();
                session.setAttribute("CATEGORYLIST", categoryList);
                if (isAdmin == false) {
                    //bestsell
                    int sellSum = dao.loadSellSum();
                    ArrayList<TblProductDTO> bestSellList = new ArrayList<>();
                    for (TblProductDTO tblProductDTO : arrListItem) {
                        if (tblProductDTO.getCount() > (0.2 * sellSum) && tblProductDTO.getQuantity() > 0) {
                            bestSellList.add(tblProductDTO);
                            tblProductDTO.setBestSell(true);
                        }
                    }
                    session.setAttribute("BESTLIST", bestSellList);
                }

            } catch (SQLException ex) {
                log("LoadItemsServlet_SQL " + ex.getMessage());
            } catch (NamingException ex) {
                log("LoadItemsServlet_Naming " + ex.getMessage());
            } finally {
                if (null != searchByName && !"".equals(searchByName.trim())) {
                    url = "SearchByNameServlet";
                } else if (null != searchByCategory && !"".equals(searchByCategory.trim())) {
                    url = "SearchByCategoryServlet";
                } else if ((null != minValue && !"".equals(minValue.trim())) || (null != maxValue && !"".equals(maxValue.trim()))) {
                    url = "SearchByPriceServlet";
                }
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
                out.close();
            }
        } else if (user == null) { // is Customer
            try {
                ArrayList<TblProductDTO> arrListItem = (ArrayList<TblProductDTO>) session.getAttribute("ITEMSLIST");
                if (arrListItem == null) {
                    arrListItem = new ArrayList<>();
                } else {
                    arrListItem.clear();
                }
                arrListItem = dao.loadStore(false);

                //get list category
                TblCategoryDAO categoryDao = new TblCategoryDAO();
                ArrayList<String> categoryList = categoryDao.loadCategory();
                session.setAttribute("CATEGORYLIST", categoryList);
                
                //bestsell
                int sellSum = dao.loadSellSum();
                ArrayList<TblProductDTO> bestSellList = new ArrayList<>();
                for (TblProductDTO tblProductDTO : arrListItem) {
                    if (tblProductDTO.getCount() > (0.2 * sellSum) && tblProductDTO.getQuantity() > 0) {
                        bestSellList.add(tblProductDTO);
                        tblProductDTO.setBestSell(true);
                    }
                }
                Collections.sort(arrListItem);
                session.setAttribute("BESTLIST", bestSellList);
                session.setAttribute("ITEMSLIST", arrListItem);

            } catch (SQLException ex) {
                log("LoadItemsServlet_SQL " + ex.getMessage());
            } catch (NamingException ex) {
                log("LoadItemsServlet_Naming " + ex.getMessage());
            } finally {
                if (null != searchByName && !"".equals(searchByName.trim())) {
                    url = "SearchByNameServlet";
                }
                if (null != searchByCategory && !"".equals(searchByCategory.trim())) {
                    url = "SearchByCategoryServlet";
                }
                if ((null != minValue && !"".equals(minValue.trim())) || (null != maxValue && !"".equals(maxValue.trim()))) {
                    url = "SearchByPriceServlet";
                }
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
                out.close();
            }
        }//is Customer 

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
