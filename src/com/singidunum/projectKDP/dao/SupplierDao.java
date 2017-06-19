package com.singidunum.projectKDP.dao;

import com.singidunum.projectKDP.data.Supplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SupplierDao {

    private static final SupplierDao INSTANCE = new SupplierDao();
    
    private SupplierDao() {}
    
    public static SupplierDao getInstance() {
        return INSTANCE;
    }
    
    public int insert(Connection conn, Supplier supplier) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = conn.prepareStatement("INSERT INTO supplier(supplier_name, contact_person, address, city, post_code, country, phone) VALUES(?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, supplier.getSupplier_name());
            ps.setString(2, supplier.getContact_person());
            ps.setString(3, supplier.getAddress());
            ps.setString(4, supplier.getCity());
            ps.setInt(5, supplier.getPost_code());
            ps.setString(6, supplier.getCountry());
            ps.setString(7, supplier.getPhone());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }     
        return id;
    }
    
    public void update(Connection conn, Supplier supplier) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("UPDATE supplier SET supplier_name=?, contact_person=?, address=?, city=?, post_code=?, country=?, phone? WHERE id_supplier=?;");            
            ps.setString(1, supplier.getSupplier_name());
            ps.setString(2, supplier.getContact_person());
            ps.setString(3, supplier.getAddress());
            ps.setString(4, supplier.getCity());
            ps.setInt(5, supplier.getPost_code());
            ps.setString(6, supplier.getCountry());
            ps.setString(7, supplier.getPhone());
            ps.setInt(8, supplier.getId_supplier());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    public void delete(Connection conn, int supplierId) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM supplier WHERE id_supplier=?;");
            ps.setInt(1, supplierId);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    public Supplier find(Connection conn, int supplierId) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Supplier supplier = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM supplier WHERE id_supplier=?;");
            ps.setInt(1, supplierId);
            rs = ps.executeQuery();
            if(rs.next()) {
                supplier = new Supplier(supplierId, rs.getString("supplier_name"), rs.getString("contact_person"), rs.getString("address"), rs.getString("city"), rs.getInt("post_code"), rs.getString("country"), rs.getString("phone"));                
            } else {
                return supplier;
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return supplier;
    }
    
    public Supplier find(Connection conn, String supplier_name) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Supplier supplier = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM supplier WHERE supplier_name=?;");
            ps.setString(1, supplier_name);
            rs = ps.executeQuery();
            if(rs.next()) {
                supplier = new Supplier(rs.getInt("id_supplier"), supplier_name, rs.getString("contact_person"), rs.getString("address"), rs.getString("city"), rs.getInt("post_code"), rs.getString("country"), rs.getString("phone"));                
            } else {
                return supplier;
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return supplier;
    }
    
    public List<Supplier> findAll(Connection conn) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Supplier> supplierList = new ArrayList<Supplier>();
        try {
            ps = conn.prepareStatement("SELECT * FROM supplier");
            rs = ps.executeQuery();
            if(!rs.next()) {
                return supplierList;
            }
            while(rs.next()) {
                Supplier supplier = new Supplier(rs.getInt("id_supplier"), rs.getString("supplier_name"), rs.getString("contact_person"), rs.getString("address"), rs.getString("city"), rs.getInt("post_code"), rs.getString("country"), rs.getString("phone"));
                supplierList.add(supplier);
            }            
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return supplierList;
    }
}
