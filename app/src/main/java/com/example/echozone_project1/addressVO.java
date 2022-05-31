package com.example.echozone_project1;

public class addressVO {

    private String shop_id;
    private String shop_pw;
    private int shop_seq;
    private String com_resid;
    private int product_seq;
    private String inst_dt;
    private String shop_nm;
    private String shop_address;

    public addressVO(){

    }

    @Override
    public String toString() {
        return "addressVO{" +
                "shop_id='" + shop_id + '\'' +
                ", shop_pw='" + shop_pw + '\'' +
                ", shop_seq=" + shop_seq +
                ", com_resid='" + com_resid + '\'' +
                ", product_seq=" + product_seq +
                ", inst_dt='" + inst_dt + '\'' +
                ", shop_nm='" + shop_nm + '\'' +
                ", shop_address='" + shop_address + '\'' +
                '}';
    }

    public addressVO(String shop_id, String shop_pw, int shop_seq, String com_resid, int product_seq, String inst_dt, String shop_nm, String shop_address) {
        this.shop_id = shop_id;
        this.shop_pw = shop_pw;
        this.shop_seq = shop_seq;
        this.com_resid = com_resid;
        this.product_seq = product_seq;
        this.inst_dt = inst_dt;
        this.shop_nm = shop_nm;
        this.shop_address = shop_address;
    }

    public String getShop_id() {
        return shop_id;
    }

    public String getShop_pw() {
        return shop_pw;
    }

    public int getShop_seq() {
        return shop_seq;
    }

    public String getCom_resid() {
        return com_resid;
    }

    public int getProduct_seq() {
        return product_seq;
    }

    public String getInst_dt() {
        return inst_dt;
    }

    public String getShop_nm() {
        return shop_nm;
    }

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public void setShop_pw(String shop_pw) {
        this.shop_pw = shop_pw;
    }

    public void setShop_seq(int shop_seq) {
        this.shop_seq = shop_seq;
    }

    public void setCom_resid(String com_resid) {
        this.com_resid = com_resid;
    }

    public void setProduct_seq(int product_seq) {
        this.product_seq = product_seq;
    }

    public void setInst_dt(String inst_dt) {
        this.inst_dt = inst_dt;
    }

    public void setShop_nm(String shop_nm) {
        this.shop_nm = shop_nm;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }
}
