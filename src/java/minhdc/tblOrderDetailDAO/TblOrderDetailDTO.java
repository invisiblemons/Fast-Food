/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.tblOrderDetailDAO;

import java.io.Serializable;

/**
 *
 * @author MONS
 */
public class TblOrderDetailDTO implements  Serializable{
    private int orderID;
    private String productID;
    private int quanity;
    private float price;
    private String productName;
    private String productImage;
    private String categoryName;

    public TblOrderDetailDTO() {
    }

    public TblOrderDetailDTO(int orderID, String productID, int quanity, float price, String productName, String productImage, String categoryName) {
        this.orderID = orderID;
        this.productID = productID;
        this.quanity = quanity;
        this.price = price;
        this.productName = productName;
        this.productImage = productImage;
        this.categoryName = categoryName;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getQuanity() {
        return quanity;
    }

    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    
}
