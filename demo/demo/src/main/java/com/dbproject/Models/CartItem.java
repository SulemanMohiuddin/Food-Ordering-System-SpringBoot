package com.dbproject.Models;

public class CartItem {
    private String itemName;
    private Double itemPrice;
    public String itemtype;
    private String itemSeller;
    private Integer restaurantId;// Add restaurantName


    // Constructors, getters, and setters

    // Default constructor
    public CartItem() {
    }


    // Parameterized constructor
    public CartItem(String itemName, Double itemPrice,String itemtype,String itemSeller,int restaurantId) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemtype=itemtype;
        this.itemSeller=itemSeller;
        this.restaurantId=restaurantId;

    }

    // Getter and Setter methods

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getItemSeller() {
        return itemSeller;
    }

    public void setItemSeller(String itemSeller) {
        this.itemSeller = itemSeller;
    }

    public String getItemtype() {
        return itemtype;
    }

    public void setItemtype(String itemtype) {
        this.itemtype = itemtype;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }


}
