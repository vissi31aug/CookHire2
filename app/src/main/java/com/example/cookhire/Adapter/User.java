package com.example.cookhire.Adapter;

public class User {

    String name, cookType, email, id, phone, profilepictureurl, search, type, setprice;


    public User(){

    }

    public User(String name, String cookType, String email, String id, String phone, String profilepictureurl, String search, String type, String setprice) {
        this.name = name;
        this.cookType = cookType;
        this.email = email;
        this.id = id;
        this.phone = phone;
        this.profilepictureurl = profilepictureurl;
        this.search = search;
        this.type = type;
        this.setprice = setprice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCookType() {
        return cookType;
    }

    public void setCookType(String cookType) {
        this.cookType = cookType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfilepictureurl() {
        return profilepictureurl;
    }

    public void setProfilepictureurl(String profilepictureurl) {
        this.profilepictureurl = profilepictureurl;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSetprice() {
        return setprice;
    }

    public void setSetprice(String setprice) {
        this.setprice = setprice;
    }
}