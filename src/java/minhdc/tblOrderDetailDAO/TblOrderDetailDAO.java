/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.tblOrderDetailDAO;

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
public class TblOrderDetailDAO implements Serializable{
    public boolean insertRecord(int idOrder, String ProductID, int quantity, float price) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBUtils.makeConnection();
            String sql = "INSERT INTO tblOrderDetail \n"
                    + "VALUES (?, ?, ?, ?);";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idOrder);
            ps.setString(2, ProductID);
            ps.setInt(3, quantity);
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
    public ArrayList<TblOrderDetailDTO> loadOrderDetail(int id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<TblOrderDetailDTO> list = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
            String sql = "SELECT D.orderID, D.productID, D.quantity, D.price, P.productName, P.productImage, G.name "
                    + "FROM tblOrderDetail D JOIN tblProduct P ON D.productID = P.productID JOIN tblCategory G ON P.categoryID = G.categoryID "
                    + "WHERE D.orderID = ?";

            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                String productID = rs.getString("productID");
                int quantity = rs.getInt("quantity");
                float price = rs.getFloat("price");
                String productName = rs.getString("productName");
                String productImage = rs.getString("productImage");
                String name = rs.getString("name");
                TblOrderDetailDTO dto = new TblOrderDetailDTO(orderID, productID, quantity, price, productName, productImage, name);
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
