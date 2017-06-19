package com.singidunum.projectKDP.dao;

import com.singidunum.projectKDP.data.Customer;
import com.singidunum.projectKDP.data.Employee;
import com.singidunum.projectKDP.data.Order;
import com.singidunum.projectKDP.data.Shipper;
import com.singidunum.projectKDP.exception.WarehouseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    private static final OrderDao INSTANCE = new OrderDao();
    
    private OrderDao() {}
    
    public static OrderDao getInstance() {
        return INSTANCE;
    }
    
    public int insert(Connection conn, Order order) throws SQLException, WarehouseException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {            
            ps = conn.prepareStatement("INSERT INTO order(order_date, fk_customer, fk_employee, fk_shipper) VALUES(?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, order.getOrder_date());
            
            Customer customer = CustomerDao.getInstance().find(conn, order.getFk_customer().getId_customer());
            if(customer == null) {
                throw new WarehouseException("Customer " + order.getFk_customer() + " doesn't exist in the database.");
            }            
            ps.setInt(2, customer.getId_customer());  
            
            Employee employee = EmployeeDao.getInstance().find(conn, order.getFk_employee().getId_employee());
            if(employee == null) {
                throw new WarehouseException("Employee " + order.getFk_employee() + " doen't exist in the database.");
            }
            ps.setInt(3, employee.getId_employee());
            
            Shipper shipper = ShipperDao.getInstance().find(conn, order.getFk_shipper().getId_shipper());
            if(shipper == null) {
                throw new WarehouseException("Shipper " + order.getFk_shipper() + " doesn't exist in the database.");
            }
            ps.setInt(4, shipper.getId_shipper());    
            
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
        return id;
    }
    
    public void update(Connection conn, Order order) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("UPDATE order SET order_date=? WHERE id_order=?;");
            ps.setDate(1, order.getOrder_date());
            ps.setInt(2, order.getId_order());
            ps.executeUpdate();
            
            if(order.getFk_customer() != null) {
                CustomerDao.getInstance().update(conn, order.getFk_customer());
            }
            
            if(order.getFk_employee() != null) {
                EmployeeDao.getInstance().update(conn, order.getFk_employee());
            }
            
            if(order.getFk_shipper() != null) {
                ShipperDao.getInstance().update(conn, order.getFk_shipper());
            }                        
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    public void delete(Connection conn, Order order) throws SQLException {
        PreparedStatement ps = null;
        try {
            OrderDetailsDao.getInstance().delete(conn, order);
            
            ps = conn.prepareStatement("DELETE FROM order WHERE id_order=?;");
            ps.setInt(1, order.getId_order());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    public Order find(Connection conn, int orderId) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Order order = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM order WHERE id_order=?;");
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            if(rs.next()) {
                Customer customer = CustomerDao.getInstance().find(conn, rs.getInt("fk_customer"));
                Employee employee = EmployeeDao.getInstance().find(conn, rs.getInt("fk_employee"));
                Shipper shipper = ShipperDao.getInstance().find(conn, rs.getInt("fk_shipper"));
                order = new Order(orderId, rs.getDate("order_date"), customer, employee, shipper);
            } else {
                return order;
            }
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
        return order;
    }
    
    public List<Order> findAll(Connection conn) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Order> orderList = new ArrayList<Order>();
        try {
            ps = conn.prepareStatement("SELECT * FROM order;");
            rs = ps.executeQuery();
            if(!rs.next()) {
                return orderList;
            }
            while(rs.next()) {
                Customer customer = CustomerDao.getInstance().find(conn, rs.getInt("fk_customer"));
                Employee employee = EmployeeDao.getInstance().find(conn, rs.getInt("fk_employee"));
                Shipper shipper = ShipperDao.getInstance().find(conn, rs.getInt("fk_shipper"));
                Order order = new Order(rs.getInt("id_order"), rs.getDate("order_date"), customer, employee, shipper);
                orderList.add(order);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return orderList;
    }
}
