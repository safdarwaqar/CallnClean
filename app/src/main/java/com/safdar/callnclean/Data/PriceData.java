package com.safdar.callnclean.Data;

public class PriceData {
    String bikePrice,sedanHatchPrice,suvPrice,sedanHatchPremium,suvPremium;

    public PriceData() {
    }

    public PriceData(String bikePrice, String sedanHatchPrice, String suvPrice, String sedanHatchPremium, String suvPremium) {
        this.bikePrice = bikePrice;
        this.sedanHatchPrice = sedanHatchPrice;
        this.suvPrice = suvPrice;
        this.sedanHatchPremium = sedanHatchPremium;
        this.suvPremium = suvPremium;
    }

    public String getBikePrice() {
        return bikePrice;
    }

    public void setBikePrice(String bikePrice) {
        this.bikePrice = bikePrice;
    }

    public String getSedanHatchPrice() {
        return sedanHatchPrice;
    }

    public void setSedanHatchPrice(String sedanHatchPrice) {
        this.sedanHatchPrice = sedanHatchPrice;
    }

    public String getSuvPrice() {
        return suvPrice;
    }

    public void setSuvPrice(String suvPrice) {
        this.suvPrice = suvPrice;
    }

    public String getSedanHatchPremium() {
        return sedanHatchPremium;
    }

    public void setSedanHatchPremium(String sedanHatchPremium) {
        this.sedanHatchPremium = sedanHatchPremium;
    }

    public String getSuvPremium() {
        return suvPremium;
    }

    public void setSuvPremium(String suvPremium) {
        this.suvPremium = suvPremium;
    }
}
