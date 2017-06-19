package com.singidunum.projectKDP.service;

import com.singidunum.projectKDP.dao.ResourcesManager;
import com.singidunum.projectKDP.exception.WarehouseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdvancedService {

    private static final AdvancedService INSTANCE = new AdvancedService();
    
    private AdvancedService() {}
    
    public static AdvancedService getInstance() {
        return INSTANCE;
    }
    
    public void first() throws WarehouseException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuilder sb = new StringBuilder();
        try {
            conn = ResourcesManager.getConnection();
            ps = conn.prepareStatement("SELECT customer_name, id_order FROM customer INNER JOIN orders ON customer.id_customer=orders.fk_customer ORDER BY customer_name ASC;");
            rs = ps.executeQuery();            
            while(rs.next()) {
                sb.append(rs.getString("customer_name")).append("\t-\t").append(rs.getInt("id_order")).append("\n");                         
            }      
            System.out.println(sb);
        } catch (SQLException ex) {
            throw new WarehouseException("Failed to select customer_name and id_order.");
        } finally {
            ResourcesManager.closeResources(rs, ps);
            ResourcesManager.closeConnection(conn);
        }
    }
    
    public void second(int supplierId) throws WarehouseException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuilder sb = new StringBuilder();
        try {
            conn = ResourcesManager.getConnection();
            ps = conn.prepareStatement("SELECT product_name, supplier_name, id_supplier FROM product INNER JOIN supplier ON product.fk_supplier=supplier.id_supplier WHERE id_supplier=?");
            ps.setInt(1, supplierId);
            rs = ps.executeQuery();
            while(rs.next()) {
                sb.append(rs.getString("product_name")).append("\t").append(rs.getString("supplier_name")).append("\t").append(rs.getInt("id_supplier")).append("\n");
            }
            System.out.println(sb);
        } catch (SQLException ex) {
            throw new WarehouseException("Failed to find products.");
        } finally {
            ResourcesManager.closeResources(rs, ps);
            ResourcesManager.closeConnection(conn);
        }
    }
    
    public void third(int shipperId) throws WarehouseException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuilder sb = new StringBuilder();
        try {
            conn = ResourcesManager.getConnection();
            ps = conn.prepareStatement("SELECT product_name, shipper_name, id_shipper FROM product INNER JOIN (order_details INNER JOIN (orders INNER JOIN shipper ON orders.fk_shipper=shipper.id_shipper) ON order_details.fk_order=orders.id_order) ON product.id_product=order_details.fk_product WHERE id_shipper=?");
            ps.setInt(1, shipperId);
            rs = ps.executeQuery();
            while(rs.next()) {                
                sb.append(rs.getString("product_name")).append("\t").append(rs.getString("shipper_name")).append("\t").append(rs.getInt("id_shipper")).append("\n");                                
            }
            System.out.println(sb);
        } catch (SQLException ex) {
            throw new WarehouseException("Failed to find products.");
        } finally {
            ResourcesManager.closeResources(rs, ps);
            ResourcesManager.closeConnection(conn);
        }
    }    
    
    public void fourth() throws WarehouseException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuilder sb = new StringBuilder();
        try {
            conn = ResourcesManager.getConnection();
            ps = conn.prepareStatement("SELECT SUM(price_per_unit) AS 'Ukupna cena svih porudzbina' FROM order_details INNER JOIN product ON order_details.fk_product=product.id_product");
            rs = ps.executeQuery();
            if(rs.next()) {                
                sb.append("Ukupna cena svih porudzbina: ").append(rs.getInt("Ukupna cena svih porudzbina")).append(" dinara");
            }
            System.out.println(sb);
        } catch (SQLException ex) {
            throw new WarehouseException("Failed to find products.");
        } finally {
            ResourcesManager.closeResources(rs, ps);
            ResourcesManager.closeConnection(conn);
        }
    }
}
