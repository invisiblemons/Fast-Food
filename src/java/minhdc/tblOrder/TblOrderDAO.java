/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.tblOrder;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import minhdc.utils.DBUtils;

/**
 *
 * @author MONS
 */
public class TblOrderDAO implements Serializable {

    public boolean insertRecord(String userID, String paymentMethod, Date date, float price) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBUtils.makeConnection();
            String sql = "INSERT INTO tblOrder \n"
                    + "VALUES (?, ?, ?, ?);";
            ps = con.prepareStatement(sql);
            ps.setString(1, userID);
            ps.setString(2, paymentMethod);
            ps.setDate(3, date);
            ps.setFloat(4, price);
            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public int getId() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            con = DBUtils.makeConnection();
            String sql = "SELECT TOP 1 orderID\n"
                    + "  FROM tblOrder\n"
                    + " ORDER BY orderID DESC;";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("orderID");

            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return id;
    }

    public ArrayList<TblOrderDTO> loadOrders(String userID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<TblOrderDTO> list = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
            String sql = "SELECT  orderID, userID, paymentMethod, dateOrder, totalPrice  "
                    + "FROM tblOrder "
                    + "WHERE userID = ? "
                    + "ORDER BY  orderID DESC";

            ps = con.prepareStatement(sql);
            ps.setString(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("orderID");
                String ID = rs.getString("userID");
                String paymentMethod = rs.getString("paymentMethod");
                Date dateOrder = rs.getDate("dateOrder");
                float totalPrice = rs.getFloat("totalPrice");
                TblOrderDTO dto = new TblOrderDTO(ID, id, paymentMethod, dateOrder, totalPrice);
                list.add(dto);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }
}
