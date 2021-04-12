/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.tblUser;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import minhdc.utils.DBUtils;

/**
 *
 * @author MONS
 */
public class TblUserDAO implements Serializable {

    public TblUserDTO checkLogin(String userID, String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "SELECT userID, password, fullName, isAdmin "
                        + "FROM dbo.tblUser "
                        + "WHERE userID = ? "
                        + "AND password = ? COLLATE SQL_Latin1_General_CP1_CS_AS";
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    TblUserDTO dto = new TblUserDTO(rs.getString("userID"), rs.getString("password"),
                             rs.getString("fullName"), rs.getBoolean("isAdmin"));
                    return dto;
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
        return null;
    }
    public boolean registerAccount(String userID, String email) throws NamingException, SQLException, NoSuchAlgorithmException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tblUser(userID, password, fullName, isAdmin) "
                        + "VALUES(?, ?, ?, ?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
                ps.setString(2, DBUtils.encryptedPasswordSHA256(email));
                ps.setString(3, email);
                ps.setBoolean(4, false);
                int rs = ps.executeUpdate();
                if (rs > 0) {
                    return true;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return false;
    }
    public TblUserDTO checkLoginGoogle(String userID, String password) throws SQLException, NamingException, NoSuchAlgorithmException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "SELECT userID, password, fullName, isAdmin "
                        + "FROM dbo.tblUser "
                        + "WHERE userID = ? "
                        + "AND password = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
                ps.setString(2, DBUtils.encryptedPasswordSHA256(password));
                rs = ps.executeQuery();
                if (rs.next()) {
                    TblUserDTO newdto = new TblUserDTO(rs.getString("userID"), rs.getString("password"),
                             rs.getString("fullName"), rs.getBoolean("isAdmin"));
                    return newdto;
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
        return null;
    }
}
