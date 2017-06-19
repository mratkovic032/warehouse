package com.singidunum.projectKDP.data;

import java.sql.Date;

public class Order {

    private int id_order;
    private Date order_date;
    private Customer fk_customer;
    private Employee fk_employee;
    private Shipper fk_shipper;

    public Order(int id_order, Date order_date, Customer fk_customer, Employee fk_employee, Shipper fk_shipper) {
        this.id_order = id_order;
        this.order_date = order_date;
        this.fk_customer = fk_customer;
        this.fk_employee = fk_employee;
        this.fk_shipper = fk_shipper;
    }

    public Order(Date order_date, Customer fk_customer, Employee fk_employee, Shipper fk_shipper) {
        this.order_date = order_date;
        this.fk_customer = fk_customer;
        this.fk_employee = fk_employee;
        this.fk_shipper = fk_shipper;
    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public Customer getFk_customer() {
        return fk_customer;
    }

    public void setFk_customer(Customer fk_customer) {
        this.fk_customer = fk_customer;
    }

    public Employee getFk_employee() {
        return fk_employee;
    }

    public void setFk_employee(Employee fk_employee) {
        this.fk_employee = fk_employee;
    }

    public Shipper getFk_shipper() {
        return fk_shipper;
    }

    public void setFk_shipper(Shipper fk_shipper) {
        this.fk_shipper = fk_shipper;
    }
    
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id_order).append("(").append(order_date).append("): ").append(fk_customer).append(", ").append(fk_employee).append(", ").append(fk_shipper);
        return sb.toString();
    }
}
