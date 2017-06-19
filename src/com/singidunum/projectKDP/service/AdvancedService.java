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
            ps = conn.prepareStatement("SELECT product_name, supplier_name, id_supplier FROM product INNER JOIN supplier ON product.fk_supplier=supplier.id_supplier WHERE id_supplier=?;");
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
            ps = conn.prepareStatement("SELECT product_name, shipper_name, id_shipper FROM product INNER JOIN (order_details INNER JOIN (orders INNER JOIN shipper ON orders.fk_shipper=shipper.id_shipper) ON order_details.fk_order=orders.id_order) ON product.id_product=order_details.fk_product WHERE id_shipper=?;");
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
        int ukupno = 0;
        try {
            conn = ResourcesManager.getConnection();
            ps = conn.prepareStatement("SELECT SUM(quantity*price_per_unit) AS 'ukupno' FROM order_details INNER JOIN product ON order_details.fk_product=product.id_product;");
            rs = ps.executeQuery();
            if(rs.next()) {           
                ukupno = rs.getInt("ukupno");
            }
            sb.append("Ukupna cena svih porudzbina: ").append(ukupno).append(" dinara.");
            System.out.println(sb);
        } catch (SQLException ex) {
            throw new WarehouseException("Failed to find SUM.");
        } finally {
            ResourcesManager.closeResources(rs, ps);
            ResourcesManager.closeConnection(conn);
        }
    }
    
    public void fifth(int customerId) throws WarehouseException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuilder sb = new StringBuilder();
        String customer_name = null;
        int ukupno = 0;
        try {
            conn = ResourcesManager.getConnection();
            ps = conn.prepareStatement("SELECT customer_name, SUM(quantity* price_per_unit) AS 'ukupno' FROM customer INNER JOIN (orders INNER JOIN (order_details INNER JOIN product ON order_details.fk_product=product.id_product) ON orders.id_order=order_details.fk_order) ON customer.id_customer=orders.fk_customer WHERE id_customer=?;");
            ps.setInt(1, customerId);
            rs = ps.executeQuery();            
            if(rs.next()) {     
                customer_name = rs.getString("customer_name");
                ukupno = rs.getInt("ukupno");
            }            
            sb.append("Customer: ").append(customer_name).append(" ima narudzbine u vrednosti od ").append(ukupno).append(" dinara.");
            System.out.println(sb);
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new WarehouseException("Failed to find SUM for specified Customer.");
        } finally {
            ResourcesManager.closeResources(rs, ps);
            ResourcesManager.closeConnection(conn);
        }
    }
    
    public void sixth(int shipperId) throws WarehouseException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuilder sb = new StringBuilder();
        String shipper_name = null;
        int ukupno = 0;
        try {
            conn = ResourcesManager.getConnection();
            ps = conn.prepareStatement("SELECT shipper_name, SUM(quantity*price_per_unit) AS 'ukupno' FROM shipper INNER JOIN (orders INNER JOIN ( order_details INNER JOIN product ON order_details.fk_product=product.id_product) ON orders.id_order=order_details.fk_order) ON shipper.id_shipper=orders.fk_shipper WHERE id_shipper=?;");
            ps.setInt(1, shipperId);
            rs = ps.executeQuery();            
            if(rs.next()) {     
                shipper_name = rs.getString("shipper_name");
                ukupno = rs.getInt("ukupno");
            }            
            sb.append("Shipper: ").append(shipper_name).append(" ima dostavljene narudzbine u vrednosti od ").append(ukupno).append(" dinara.");
            System.out.println(sb);
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new WarehouseException("Failed to find SUM for specified Shipper.");
        } finally {
            ResourcesManager.closeResources(rs, ps);
            ResourcesManager.closeConnection(conn);
        }
    }
    
    public void seventh(int supplierId) throws WarehouseException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuilder sb = new StringBuilder();
        String supplier_name = null;
        int ukupno = 0;
        try {
            conn = ResourcesManager.getConnection();
            ps = conn.prepareStatement("SELECT supplier_name, SUM(quantity*price_per_unit) AS 'ukupno' FROM order_details INNER JOIN (product INNER JOIN supplier ON product.fk_supplier=supplier.id_supplier) ON order_details.fk_product=product.id_product WHERE id_supplier=?;");
            ps.setInt(1, supplierId);
            rs = ps.executeQuery();            
            if(rs.next()) {     
                supplier_name = rs.getString("supplier_name");
                ukupno = rs.getInt("ukupno");
            }            
            sb.append("Supplier: ").append(supplier_name).append(" ima dobavljene narudzbine u vrednosti od ").append(ukupno).append(" dinara.");
            System.out.println(sb);
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new WarehouseException("Failed to find SUM for specified Supplier.");
        } finally {
            ResourcesManager.closeResources(rs, ps);
            ResourcesManager.closeConnection(conn);
        }
    } 

    public void eighth() throws WarehouseException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuilder sb = new StringBuilder();
        String last_name = null;
        String first_name = null;
        int ukupno = 0;
        try {
            conn = ResourcesManager.getConnection();
            ps = conn.prepareStatement("SELECT last_name, first_name, MAX(quantity*price_per_unit) AS 'ukupno' FROM employee INNER JOIN (orders INNER JOIN (order_details INNER JOIN product ON order_details.fk_product=product.id_product) ON orders.id_order=order_details.fk_order) ON employee.id_employee=orders.fk_employee;");
            rs = ps.executeQuery();            
            if(rs.next()) {     
                last_name = rs.getString("last_name");
                first_name = rs.getString("first_name");
                ukupno = rs.getInt("ukupno");
            }            
            sb.append("Employee: ").append(last_name).append(" ").append(first_name).append(" ima najvecu kolicinu narucene robe u vrednosti od ").append(ukupno).append(" dinara.");
            System.out.println(sb);
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new WarehouseException("Failed to find MAX for specified Employee.");
        } finally {
            ResourcesManager.closeResources(rs, ps);
            ResourcesManager.closeConnection(conn);
        }
    }
    
    public void ninth() throws WarehouseException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuilder sb = new StringBuilder();
        sb.append("Dva najvise porucivana Product-a su:\n");
        String product_name = null;
        int porudzbine = 0;        
        try {
            conn = ResourcesManager.getConnection();
            ps = conn.prepareStatement("SELECT product_name, fk_product, COUNT(fk_product) AS 'porudzbina' FROM order_details INNER JOIN product ON order_details.fk_product=product.id_product GROUP BY fk_product ORDER BY porudzbina DESC LIMIT 2;");
            rs = ps.executeQuery();            
            while(rs.next()) {                     
                sb.append(rs.getString("product_name")).append("\t").append(rs.getInt("porudzbina")).append("\t\n");
            }                        
            System.out.println(sb);
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new WarehouseException("Failed to find 2 product.");
        } finally {
            ResourcesManager.closeResources(rs, ps);
            ResourcesManager.closeConnection(conn);
        }
    }
}
