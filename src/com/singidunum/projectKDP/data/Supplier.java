package com.singidunum.projectKDP.data;

public class Supplier {

    private int id_supplier;
    private String supplier_name;
    private String contact_person;
    private String address;
    private String city;
    private int post_code;
    private String country;
    private String phone;

    public Supplier(int id_supplier, String supplier_name, String contact_person, String address, String city, int post_code, String country, String phone) {
        this.id_supplier = id_supplier;
        this.supplier_name = supplier_name;
        this.contact_person = contact_person;
        this.address = address;
        this.city = city;
        this.post_code = post_code;
        this.country = country;
        this.phone = phone;
    }

    public Supplier(String supplier_name, String contact_person, String address, String city, int post_code, String country, String phone) {
        this.supplier_name = supplier_name;
        this.contact_person = contact_person;
        this.address = address;
        this.city = city;
        this.post_code = post_code;
        this.country = country;
        this.phone = phone;
    }

    public int getId_supplier() {
        return id_supplier;
    }

    public void setId_supplier(int id_supplier) {
        this.id_supplier = id_supplier;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPost_code() {
        return post_code;
    }

    public void setPost_code(int post_code) {
        this.post_code = post_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
        sb.append(id_supplier).append(": ").append(supplier_name).append(", ").append(contact_person).append("(").append(country).append(", ").append(city).append(", ").append(address).append(", ").append(post_code).append(", Phone:").append(phone).append(")");
        return sb.toString();
    }
}
