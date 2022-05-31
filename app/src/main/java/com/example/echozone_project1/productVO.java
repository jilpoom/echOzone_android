package com.example.echozone_project1;

public class productVO {

    private int product_seq;
    private String product_cap;
    private String manufacture_dt;
    private String shop_id;

    public productVO(){

    }

    @Override
    public String toString() {
        return "productVO{" +
                "product_seq=" + product_seq +
                ", product_cap='" + product_cap + '\'' +
                ", manufacture_dt='" + manufacture_dt + '\'' +
                ", shop_id='" + shop_id + '\'' +
                '}';
    }

    public productVO(int product_seq, String product_cap, String manufacture_dt, String shop_id) {
        this.product_seq = product_seq;
        this.product_cap = product_cap;
        this.manufacture_dt = manufacture_dt;
        this.shop_id = shop_id;
    }

    public int getProduct_seq() {
        return product_seq;
    }

    public void setProduct_seq(int product_seq) {
        this.product_seq = product_seq;
    }

    public String getProduct_cap() {
        return product_cap;
    }

    public void setProduct_cap(String product_cap) {
        this.product_cap = product_cap;
    }

    public String getManufacture_dt() {
        return manufacture_dt;
    }

    public void setManufacture_dt(String manufacture_dt) {
        this.manufacture_dt = manufacture_dt;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }
}
