package com.eatit.user.eatit.Model;

import java.util.List;

public class Requests {
    private String name, phone, address, total;
    private List<Orders> foodList;

    public Requests() {
    }

    public Requests(String name, String phone, String address, String total, List<Orders> foodList) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.total = total;
        this.foodList = foodList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Orders> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Orders> foodList) {
        this.foodList = foodList;
    }
}
