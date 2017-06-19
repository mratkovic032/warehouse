package com.singidunum.projectKDP.data;

public class OrderDetails {

    private int id_order_details;
    private Order fk_order;
    private Product fk_product;
    private int quantity;

    public OrderDetails(int id_order_details, Order fk_order, Product fk_product, int quantity) {
        this.id_order_details = id_order_details;
        this.fk_order = fk_order;
        this.fk_product = fk_product;
        this.quantity = quantity;
    }

    public OrderDetails(Order fk_order, Product fk_product, int quantity) {
        this.fk_order = fk_order;
        this.fk_product = fk_product;
        this.quantity = quantity;
    }

    public int getId_order_details() {
        return id_order_details;
    }

    public void setId_order_details(int id_order_details) {
        this.id_order_details = id_order_details;
    }

    public Order getFk_order() {
        return fk_order;
    }

    public void setFk_order(Order fk_order) {
        this.fk_order = fk_order;
    }

    public Product getFk_product() {
        return fk_product;
    }

    public void setFk_product(Product fk_product) {
        this.fk_product = fk_product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id_order_details).append(": ").append(fk_order).append(", ").append(fk_product).append(", ").append(quantity);
        return sb.toString();                
    }
}
