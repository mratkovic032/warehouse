package com.singidunum.projectKDP.data;

public class Customer {

    private int id_customer;    
    private String customer_name;
    private String contact_person;
    private String address;
    private String city;    
    private int post_code;
    private String country;

    public Customer(int id_customer, String customer_name, String contact_person, String address, String city, int post_code, String country) {
        this.id_customer = id_customer;
        this.customer_name = customer_name;
        this.contact_person = contact_person;
        this.address = address;
        this.city = city;
        this.post_code = post_code;
        this.country = country;
    }
    
    public Customer(String customer_name, String contact_person, String address, String city, int post_code, String country) {
        this.customer_name = customer_name;
        this.contact_person = contact_person;
        this.address = address;
        this.city = city;
        this.post_code = post_code;
        this.country = country;
    }

    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
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

    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id_customer).append(": ").append(customer_name).append(", ").append(contact_person).append("(").append(country).append(", ").append(city).append(", ").append(address).append(", ").append(post_code).append(")");
        return sb.toString();
    }
}
