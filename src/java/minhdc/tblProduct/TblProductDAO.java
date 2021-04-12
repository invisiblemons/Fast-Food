/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.tblProduct;

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
public class TblProductDAO implements Serializable{

    public ArrayList<TblProductDTO> loadStore(boolean role) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<TblProductDTO> list = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
            String sql;
            if (role)//is admin
            {
                sql = "SELECT P.productID,P.productName,P.productImage,P.productDescription,P.productPrice,P.createDate,P.lastUpdate,C.name,P.userUpdate,P.quantity,P.status, P.count "
                        + "FROM tblProduct P JOIN tblCategory C ON P.categoryID = C.categoryID ";
            } else//is customer
            {
                sql = "SELECT P.productID,P.productName,P.productImage,P.productDescription,P.productPrice,P.createDate,P.lastUpdate,C.name,P.userUpdate,P.quantity,P.status, P.count "
                        + "FROM tblProduct P JOIN tblCategory C ON P.categoryID = C.categoryID "
                        + "WHERE P.status = 1 "
                        + "ORDER BY  P.count DESC";
            }
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String productID = rs.getString("productID");
                String productName = rs.getString("productName");
                String productImage = rs.getString("productImage");
                String productDescription = rs.getString("productDescription");
                float productPrice = rs.getFloat("productPrice");
                Date createDate = rs.getDate("createDate");
                Date lastUpdate = rs.getDate("lastUpdate");
                String category = rs.getString("name");
                String userUpdate = rs.getString("userUpdate");
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");
                int count = rs.getInt("count");
                TblProductDTO product = new TblProductDTO(productID, productName, productImage, productDescription, productPrice, createDate, lastUpdate, category, userUpdate, quantity, status, count, false);
                list.add(product);
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

    public ArrayList<TblProductDTO> loadNameSearchResult(String name, boolean role) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<TblProductDTO> list = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
            String sql;
            if (role)//is admin
            {
                sql = "SELECT P.productID,P.productName,P.productImage,P.productDescription,P.productPrice,P.createDate,P.lastUpdate,C.name,P.userUpdate,P.quantity,P.status, P.count "
                        + "FROM tblProduct P JOIN tblCategory C ON P.categoryID = C.categoryID "
                        + "WHERE productName LIKE ?";
            } else//is customer
            {
                sql = "SELECT P.productID,P.productName,P.productImage,P.productDescription,P.productPrice,P.createDate,P.lastUpdate,C.name,P.userUpdate,P.quantity,P.status, P.count "
                        + "FROM tblProduct P JOIN tblCategory C ON P.categoryID = C.categoryID "
                        + "WHERE productName LIKE ? AND status = 1 ";
            }
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String productID = rs.getString("productID");
                String productName = rs.getString("productName");
                String productImage = rs.getString("productImage");
                String productDescription = rs.getString("productDescription");
                float productPrice = rs.getFloat("productPrice");
                Date createDate = rs.getDate("createDate");
                Date lastUpdate = rs.getDate("lastUpdate");
                String category = rs.getString("name");
                String userUpdate = rs.getString("userUpdate");
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");
                int count = rs.getInt("count");
                TblProductDTO products = new TblProductDTO(productID, productName, productImage, productDescription, productPrice, createDate, lastUpdate, category, userUpdate, quantity, status, count, false);
                list.add(products);
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

    public ArrayList<TblProductDTO> loadPriceSearchResult(float minPrice, float maxPrice, boolean role) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<TblProductDTO> list = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
            String sql;
            if (role)//is admin
            {
                sql = "SELECT P.productID,P.productName,P.productImage,P.productDescription,P.productPrice,P.createDate,P.lastUpdate,C.name,P.userUpdate,P.quantity,P.status, P.count "
                        + "FROM tblProduct P JOIN tblCategory C ON P.categoryID = C.categoryID "
                        + "WHERE productPrice >= ? AND productPrice <= ?";
            } else//is customer
            {
                sql = "SELECT P.productID,P.productName,P.productImage,P.productDescription,P.productPrice,P.createDate,P.lastUpdate,C.name,P.userUpdate,P.quantity,P.status, P.count "
                        + "FROM tblProduct P JOIN tblCategory C ON P.categoryID = C.categoryID "
                        + "WHERE productPrice >= ? AND productPrice <= ? AND status = 1 ";
            }
            ps = con.prepareStatement(sql);
            ps.setFloat(1, minPrice);
            ps.setFloat(2, maxPrice);
            rs = ps.executeQuery();
            while (rs.next()) {
                String productID = rs.getString("productID");
                String productName = rs.getString("productName");
                String productImage = rs.getString("productImage");
                String productDescription = rs.getString("productDescription");
                float productPrice = rs.getFloat("productPrice");
                Date createDate = rs.getDate("createDate");
                Date lastUpdate = rs.getDate("lastUpdate");
                String category = rs.getString("name");
                String userUpdate = rs.getString("userUpdate");
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");
                int count = rs.getInt("count");
                TblProductDTO products = new TblProductDTO(productID, productName, productImage, productDescription, productPrice, createDate, lastUpdate, category, userUpdate, quantity, status, count, false);

                list.add(products);
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

    public ArrayList<TblProductDTO> loadCategorySearchResult(String name, boolean role) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<TblProductDTO> list = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
            String sql;
            if (role)//is admin
            {
                sql = "SELECT P.productID,P.productName,P.productImage,P.productDescription,P.productPrice,P.createDate,P.lastUpdate,C.name,P.userUpdate,P.quantity,P.status, P.count "
                        + "FROM tblProduct P JOIN tblCategory C ON P.categoryID = C.categoryID "
                        + "WHERE C.name = ?";
            } else//is customer
            {
                sql = "SELECT P.productID,P.productName,P.productImage,P.productDescription,P.productPrice,P.createDate,P.lastUpdate,C.name,P.userUpdate,P.quantity,P.status, P.count "
                        + "FROM tblProduct P JOIN tblCategory C ON P.categoryID = C.categoryID "
                        + "WHERE C.name = ? AND status = 1 ";
            }
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                String productID = rs.getString("productID");
                String productName = rs.getString("productName");
                String productImage = rs.getString("productImage");
                String productDescription = rs.getString("productDescription");
                float productPrice = rs.getFloat("productPrice");
                Date createDate = rs.getDate("createDate");
                Date lastUpdate = rs.getDate("lastUpdate");
                String category = rs.getString("name");
                String userUpdate = rs.getString("userUpdate");
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");
                int count = rs.getInt("count");
                TblProductDTO products = new TblProductDTO(productID, productName, productImage, productDescription, productPrice, createDate, lastUpdate, category, userUpdate, quantity, status, count, false);

                list.add(products);
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
    public boolean deleteProduct(String productID, String lastUserDelete, Date lastUpdate, boolean status)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "UPDATE tblProduct "
                        + "SET lastUpdate = ?, userUpdate = ?, status = ? "
                        + "WHERE productID = ?";
                ps = con.prepareStatement(sql);
                ps.setDate(1, lastUpdate);
                ps.setString(2, lastUserDelete);
                ps.setBoolean(3, status);
                ps.setString(4, productID);
                int result = ps.executeUpdate();
                if (result > 0) {
                    return true;
                }
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
    public boolean UpdateProduct(String productID, String productName,
            String productImage, String productDescription, float productPrice,
            boolean status, int quantity, String lastUserUpdate, Date lastUpdate, String categoryID)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "UPDATE tblProduct "
                        + "SET productName = ?, productImage = ?, productDescription = ?, productPrice = ?, "
                        + "lastUpdate = ?, userUpdate = ?, quantity = ?, status = ?, categoryID =? "
                        + "WHERE productID = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, productName);
                ps.setString(2, productImage);
                ps.setString(3, productDescription);
                ps.setFloat(4, productPrice);
                ps.setDate(5, lastUpdate);
                ps.setString(6, lastUserUpdate);
                ps.setInt(7, quantity);
                ps.setBoolean(8, status);
                ps.setString(9, categoryID);
                ps.setString(10, productID);
                int result = ps.executeUpdate();
                if (result > 0) {
                    return true;
                }
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
    public boolean CheckDuplicateProductID(String productID)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "SELECT productID "
                        + "FROM tblProduct "
                        + "WHERE productID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, productID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return true;
                }
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
        return false;
    }

    
    
    public boolean CreateNewProduct(String productID, String productName,
            String productImage, String productDescription, float productPrice,
            Date createDate, boolean status, int quantity, String userInsert, String categoryID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tblProduct "
                        + "(productID, productName, productImage, productDescription, productPrice, createDate, lastUpdate, userUpdate, quantity, status, categoryID, count ) "
                        + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

                ps = con.prepareStatement(sql);
                ps.setString(1, productID);
                ps.setString(2, productName);
                ps.setString(3, productImage);
                ps.setString(4, productDescription);
                ps.setFloat(5, productPrice);
                ps.setDate(6, createDate);
                ps.setDate(7, createDate);
                ps.setString(8, userInsert);
                ps.setInt(9, quantity);
                ps.setBoolean(10, status);
                ps.setString(11, categoryID);
                ps.setInt(12, 0);
                int result = ps.executeUpdate();
                if (result > 0) {
                    return true;
                }
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
    
    public TblProductDTO getProduct(String id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TblProductDTO product = new TblProductDTO();
        try {
            con = DBUtils.makeConnection();
            String sql = "SELECT P.productID,P.productName,P.productImage,P.productDescription,P.productPrice,P.createDate,P.lastUpdate,C.name,P.userUpdate,P.quantity,P.status, P.count "
                        + "FROM tblProduct P JOIN tblCategory C ON P.categoryID = C.categoryID "
                        + "WHERE P.productID = ?";
            
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
               String productID = rs.getString("productID");
                String productName = rs.getString("productName");
                String productImage = rs.getString("productImage");
                String productDescription = rs.getString("productDescription");
                float productPrice = rs.getFloat("productPrice");
                Date createDate = rs.getDate("createDate");
                Date lastUpdate = rs.getDate("lastUpdate");
                String category = rs.getString("name");
                String userUpdate = rs.getString("userUpdate");
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");
                int count = rs.getInt("count");
                product = new TblProductDTO(productID, productName, productImage, productDescription, productPrice, createDate, lastUpdate, category, userUpdate, quantity, status, count, false);

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
        return product;
    }
    
    public boolean updateQuantity(String id, int quantity) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = DBUtils.makeConnection();
            String sql = "UPDATE dbo.tblProduct\n "
                    + "SET quantity = ?\n "
                    + "WHERE productID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(2, id);
            ps.setInt(1, quantity);
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
    public boolean updateStatus(String id, boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = DBUtils.makeConnection();
            String sql = "UPDATE dbo.tblProduct\n "
                    + "SET status = ?\n "
                    + "WHERE productID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(2, id);
            ps.setBoolean(1, status);
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
    public int loadSellSum() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int sum = 0;
        try {
            con = DBUtils.makeConnection();
            String sql = "SELECT  P.count "
                        + "FROM tblProduct P JOIN tblCategory C ON P.categoryID = C.categoryID "
                        + "WHERE P.status = 1";
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                sum = sum + rs.getInt("count");
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
        return sum;
    }
    public boolean updateCount(String id, int count) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = DBUtils.makeConnection();
            String sql = "UPDATE dbo.tblProduct\n "
                    + "SET count = ?\n "
                    + "WHERE productID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(2, id);
            ps.setInt(1, count);
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
}
