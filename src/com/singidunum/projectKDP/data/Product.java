package com.singidunum.projectKDP.data;

public class Product {

    private int id_product;
    private String product_name;
    private Supplier fk_supplier;
    private String product_category;
    private double price_per_unit;

    public Product(int id_product, String product_name, Supplier fk_supplier, String product_category, double price_per_unit) {
        this.id_product = id_product;
        this.product_name = product_name;
        this.fk_supplier = fk_supplier;
        this.product_category = product_category;
        this.price_per_unit = price_per_unit;
    }

    public Product(String product_name, Supplier fk_supplier, String product_category, double price_per_unit) {
        this.product_name = product_name;
        this.fk_supplier = fk_supplier;
        this.product_category = product_category;
        this.price_per_unit = price_per_unit;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Supplier getFk_supplier() {
        return fk_supplier;
    }

    public void setFk_supplier(Supplier fk_supplier) {
        this.fk_supplier = fk_supplier;
    }

    public String getProduct_category() {
        return product_category;
    }

    public void setProduct_category(String product_category) {
        this.product_category = product_category;
    }

    public double getPrice_per_unit() {
        return price_per_unit;
    }

    public void setPrice_per_unit(double price_per_unit) {
        this.price_per_unit = price_per_unit;
    }        
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id_product).append(": ").append(product_name).append(", ").append(product_category).append(", ").append(price_per_unit).append(", ").append(fk_supplier);
        return sb.toString();
    }
}
