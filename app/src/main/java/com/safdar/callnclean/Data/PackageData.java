package com.safdar.callnclean.Data;

public class PackageData {
    String itemName, itemDesc, itemPrice, itemImage;
    Boolean AddedToCart;

    public PackageData() {
    }

    public PackageData(String itemName, String itemDesc, String itemPrice, String itemImage, Boolean addedToCart) {
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.itemPrice = itemPrice;
        this.itemImage = itemImage;
        AddedToCart = addedToCart;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public Boolean getAddedToCart() {
        return AddedToCart;
    }

    public void setAddedToCart(Boolean addedToCart) {
        AddedToCart = addedToCart;
    }
}
