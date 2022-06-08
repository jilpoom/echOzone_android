package com.example.echozone_project1;

public class mileageVO {

    private String user_id;
    private int user_mileage;
    private String mileage_dt;
    private String shop_nm;
    private String shop_address;
    private int count;

    // 리스트뷰 구현
    public int mainImage = 0;

    public String mileage;
    public String title = "";
    public String body_1 = "";
    public String body_2 = "";
    public String body_3 = "";
    public String cnt;

    public mileageVO(){

    }

    @Override
    public String toString() {
        return "mileageVO{" +
                "user_id='" + user_id + '\'' +
                ", user_mileage=" + user_mileage +
                ", mileage_dt='" + mileage_dt + '\'' +
                ", shop_nm='" + shop_nm + '\'' +
                ", shop_address='" + shop_address + '\'' +
                ", count='" + count + '\'' +
                '}';
    }

    public mileageVO(String user_id, int user_mileage, String mileage_dt, String shop_nm, String shop_address, int count) {
        this.user_id = user_id;
        this.user_mileage = user_mileage;
        this.mileage_dt = mileage_dt;
        this.shop_nm = shop_nm;
        this.shop_address = shop_address;
        this.count = count;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getUser_mileage() {
        return user_mileage;
    }

    public void setUser_mileage(int user_mileage) {
        this.user_mileage = user_mileage;
    }

    public String getMileage_dt() {
        return mileage_dt;
    }

    public void setMileage_dt(String mileage_dt) {
        this.mileage_dt = mileage_dt;
    }

    public String getShop_nm() {
        return shop_nm;
    }

    public void setShop_nm(String shop_nm) {
        this.shop_nm = shop_nm;
    }

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
