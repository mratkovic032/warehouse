package com.singidunum.projectKDP.dao;

import com.singidunum.projectKDP.data.Product;
import com.singidunum.projectKDP.data.Supplier;
import com.singidunum.projectKDP.exception.WarehouseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    private static final ProductDao INSTANCE = new ProductDao();
    
    private ProductDao() {}
    
    public static ProductDao getInstance() {
        return INSTANCE;
    }
    
    public void insert(Connection conn, Product product) throws SQLException, WarehouseException {
        PreparedStatement ps = null;
        try {          
            Integer fk_supplier = null;
            if(product.getFk_supplier() != null) {                
                fk_supplier = SupplierDao.getInstance().insert(conn, product.getFk_supplier());
            }
            
            ps = conn.prepareStatement("INSERT INTO product(product_name, fk_supplier, product_category, price_per_unit) VALUES(?, ?, ?, ?);");
            ps.setString(1, product.getProduct_name());
            ps.setInt(2, fk_supplier);
            ps.setString(3, product.getProduct_category());
            ps.setDouble(4, product.getPrice_per_unit());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    public void update(Connection conn, Product product) throws SQLException {
        PreparedStatement ps = null;
        try {            
            ps = conn.prepareStatement("UPDATE product SET product_name=?, fk_supplier=?, product_category=?, price_per_unit=? WHERE id_product=?;");
            ps.setString(1, product.getProduct_name());
            ps.setInt(2, product.getFk_supplier().getId_supplier());
            ps.setString(3, product.getProduct_category());
            ps.setDouble(4, product.getPrice_per_unit());
            ps.setInt(5, product.getId_product());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    public void delete(Connection conn, int productIt) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM product WHERE id_product=?;");
            ps.setInt(1, productIt);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    public Product find(Connection conn, int productId) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Product product = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM product WHERE id_product=?;");
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            if(rs.next()) {
                Supplier supplier = SupplierDao.getInstance().find(conn, rs.getInt("id_supplier"));
                product = new Product(productId, rs.getString("product_name"), supplier, rs.getString("product_category"), rs.getDouble("price_per_unit"));
            } else {
                return product;
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return product;
    }
    
    public List<Product> findAll(Connection conn) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Product product = null;
        List<Product> products = new ArrayList<Product>();
        try {
            ps = conn.prepareStatement("SELECT * FROM product;");
            rs = ps.executeQuery();
            if(!rs.next()) {
                return products;
            }
            while(rs.next()) {
                Supplier supplier = SupplierDao.getInstance().find(conn, rs.getInt("id_supplier"));
                product = new Product(rs.getInt("id_product"), rs.getString("product_name"), supplier, rs.getString("product_category"), rs.getDouble("price_per_unit"));
                products.add(product);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return products;
    }
}
