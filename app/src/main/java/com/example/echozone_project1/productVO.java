package com.example.echozone_project1;

public class productVO {

    private int product_seq;
    private int product_cap;
    private String shop_id;
    private int current_cap;

    public productVO() {

    }

    @Override
    public String toString() {
        return "productVO{" +
                "product_seq=" + product_seq +
                ", product_cap=" + product_cap +
                ", shop_id='" + shop_id + '\'' +
                ", current_cap=" + current_cap +
                '}';
    }

    public productVO(int product_seq, int product_cap, String shop_id, int current_cap) {
        this.product_seq = product_seq;
        this.product_cap = product_cap;
        this.shop_id = shop_id;
        this.current_cap = current_cap;
    }

    public int getProduct_seq() {
        return product_seq;
    }

    public void setProduct_seq(int product_seq) {
        this.product_seq = product_seq;
    }

    public int getProduct_cap() {
        return product_cap;
    }

    public void setProduct_cap(int product_cap) {
        this.product_cap = product_cap;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public int getCurrent_cap() {
        return current_cap;
    }

    public void setCurrent_cap(int current_cap) {
        this.current_cap = current_cap;
    }
}