/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.tblProduct;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author MONS
 */
public class TblProductDTO implements Comparable<TblProductDTO>, Serializable{
    private String productID;
    private String productName;
    private String productImage;
    private String productDescription;
    private float productPrice;
    private Date createDate;
    private Date lastUpdate;
    private String category;
    private String userUpdate;
    private int quantity;
    private int quancart;
    private boolean status;
    private int count;
    private boolean bestSell;

    public TblProductDTO() {
    }

    
    public TblProductDTO(String productID, String productName, String productImage, String productDescription, float productPrice, Date createDate, Date lastUpdate, String category, String userUpdate, int quantity, boolean status, int count, boolean bestSell) {
        this.productID = productID;
        this.productName = productName;
        this.productImage = productImage;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
        this.category = category;
        this.userUpdate = userUpdate;
        this.quantity = quantity;
        this.status = status;
        this.count = count;
        this.bestSell = bestSell;
    }

    public TblProductDTO(String productID, String productName, String productImage, float productPrice, Date lastUpdate, String category, String userUpdate, int quantity, int quancart) {
        this.productID = productID;
        this.productName = productName;
        this.productImage = productImage;
        this.productPrice = productPrice;
        this.lastUpdate = lastUpdate;
        this.category = category;
        this.userUpdate = userUpdate;
        this.quantity = quantity;
        this.quancart = quancart;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isBestSell() {
        return bestSell;
    }

    public void setBestSell(boolean bestSell) {
        this.bestSell = bestSell;
    }
    

    
    public int getQuancart() {
        return quancart;
    }

    public void setQuancart(int quancart) {
        this.quancart = quancart;
    }
    
    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
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

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(String userUpdate) {
        this.userUpdate = userUpdate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int compareTo(TblProductDTO o) {
        return -(this.getLastUpdate().compareTo(o.getLastUpdate()));
    }
}
