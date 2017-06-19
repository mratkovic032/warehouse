package com.singidunum.projectKDP.dao;

import com.singidunum.projectKDP.data.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

    private static final CustomerDao INSTANCE = new CustomerDao();
    
    private CustomerDao() {}
    
    public static CustomerDao getInstance() {
        return INSTANCE;
    }
    
    public int insert(Connection conn, Customer customer) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = conn.prepareStatement("INSERT INTO customer(customer_name, contact_person, address, city, post_code, country) VALUES(?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, customer.getCustomer_name());
            ps.setString(2, customer.getContact_person());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getCity());
            ps.setInt(5, customer.getPost_code());
            ps.setString(6, customer.getCountry());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
        return id;
    }
    
    public void update(Connection conn, Customer customer) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("UPDATE customer SET customer_name=?, contact_person=?, address=?, city=?, post_code=?, country=? WHERE id_customer=?;");
            ps.setString(1, customer.getCustomer_name());
            ps.setString(2, customer.getContact_person());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getCity());
            ps.setInt(5, customer.getPost_code());
            ps.setString(6, customer.getCountry());
            ps.setInt(7, customer.getId_customer());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    public void delete(Connection conn, int customerId) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM customer WHERE id_customer=?;");
            ps.setInt(1, customerId);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    public Customer find(Connection conn, int customerId) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Customer customer = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM customer WHERE id_customer=?;");
            ps.setInt(1, customerId);
            rs = ps.executeQuery();
            if(rs.next()) {
                customer = new Customer(customerId, rs.getString("customer_name"), rs.getString("contact_person"), rs.getString("address"), rs.getString("city"), rs.getInt("post_code"), rs.getString("country"));
            } else {
                return customer;
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return customer;
    }
    
    public List<Customer> findAll(Connection conn) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Customer> customerList = new ArrayList<Customer>();
        try {
            ps = conn.prepareStatement("SELECT * FROM customer;");
            rs = ps.executeQuery();
            if(!rs.next()) {
                return customerList;
            }
            while(rs.next()) {
                Customer customer = new Customer(rs.getInt("id_customer"), rs.getString("customer_name"), rs.getString("contact_person"), rs.getString("address"), rs.getString("city"), rs.getInt("post_code"), rs.getString("country"));
                customerList.add(customer);
            }             
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return customerList;
    }        
}
