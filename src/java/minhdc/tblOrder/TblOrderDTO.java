/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.tblOrder;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author MONS
 */
public class TblOrderDTO implements Serializable{
    private String userID;
    private int orderID;
    private String paymentMethod;
    private Date dateOrder;
    private float totalPrice;

    public TblOrderDTO(String userID, int OrderID, String paymentMethod, Date dateOrder, float totalPrice) {
        this.userID = userID;
        this.orderID = OrderID;
        this.paymentMethod = paymentMethod;
        this.dateOrder = dateOrder;
        this.totalPrice = totalPrice;
    }

    public TblOrderDTO() {
    }

    
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int OrderID) {
        this.orderID = OrderID;
    }

    

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    
}
