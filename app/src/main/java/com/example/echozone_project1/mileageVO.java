package com.example.echozone_project1;

public class mileageVO {

    private int mileage_seq;
    private String user_id;
    private String shop_id;
    private int user_mileage;
    private String mileage_dt;

    public mileageVO(){

    }

    @Override
    public String toString() {
        return "mileageVO{" +
                "mileage_seq=" + mileage_seq +
                ", user_id='" + user_id + '\'' +
                ", shop_id='" + shop_id + '\'' +
                ", user_mileage=" + user_mileage +
                ", mileage_dt='" + mileage_dt + '\'' +
                '}';
    }

    public mileageVO(int mileage_seq, String user_id, String shop_id, int user_mileage, String mileage_dt) {
        this.mileage_seq = mileage_seq;
        this.user_id = user_id;
        this.shop_id = shop_id;
        this.user_mileage = user_mileage;
        this.mileage_dt = mileage_dt;
    }

    public int getMileage_seq() {
        return mileage_seq;
    }

    public void setMileage_seq(int mileage_seq) {
        this.mileage_seq = mileage_seq;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
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
}
