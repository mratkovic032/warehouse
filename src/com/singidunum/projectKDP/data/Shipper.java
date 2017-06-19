package com.singidunum.projectKDP.data;

public class Shipper {

    private int id_shipper;
    private String shipper_name;
    private String phone;

    public Shipper(int id_shipper, String shipper_name, String phone) {
        this.id_shipper = id_shipper;
        this.shipper_name = shipper_name;
        this.phone = phone;
    }

    public Shipper(String shipper_name, String phone) {
        this.shipper_name = shipper_name;
        this.phone = phone;
    }

    public int getId_shipper() {
        return id_shipper;
    }

    public void setId_shipper(int id_shipper) {
        this.id_shipper = id_shipper;
    }

    public String getShipper_name() {
        return shipper_name;
    }

    public void setShipper_name(String shipper_name) {
        this.shipper_name = shipper_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }        
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id_shipper).append(": ").append(shipper_name).append(", ").append(phone);
        return sb.toString();
    }
}
