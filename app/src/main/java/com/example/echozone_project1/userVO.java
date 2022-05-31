package com.example.echozone_project1;

public class userVO {

    private String id;
    private String pw;
    private String type;
    private String phone;
    private String address;
    private String name;
    private String joinDate;

    public userVO(){

    }

    public userVO(String id, String pw, String type, String phone, String address, String name, String joinDate) {
        this.id = id;
        this.pw = pw;
        this.type = type;
        this.phone = phone;
        this.address = address;
        this.name = name;
        this.joinDate = joinDate;
    }

    @Override
    public String toString() {
        return "MemberVO{" +
                "id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", type='" + type + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", joinDate='" + joinDate + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

}
