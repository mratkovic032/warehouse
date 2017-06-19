package com.singidunum.projectKDP.dao;

import com.singidunum.projectKDP.data.OrderDetails;
import com.singidunum.projectKDP.data.Order;
import com.singidunum.projectKDP.data.Product;
import com.singidunum.projectKDP.exception.WarehouseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDao {

    private static final OrderDetailsDao INSTANCE = new OrderDetailsDao();
    
    private OrderDetailsDao() {}
    
    public static OrderDetailsDao getInstance() {
        return INSTANCE;
    }
    
    public void insert(Connection conn, OrderDetails orderDetails) throws SQLException, WarehouseException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("INSERT INTO order_details(fk_order, fk_product, quantity) VALUES(?, ?, ?, ?);");
            
            Integer fk_order = null;
            if(orderDetails.getFk_order() != null) {
                fk_order = OrderDao.getInstance().insert(conn, orderDetails.getFk_order());
            }
            ps.setInt(1, fk_order);
            
            Product product = ProductDao.getInstance().find(conn, orderDetails.getFk_product().getId_product());
            if(product == null) {
                throw new WarehouseException("Product " + orderDetails.getFk_product() + " doesn't exist in database.");
            }
            ps.setInt(2, product.getId_product());
            
            ps.setInt(3, orderDetails.getQuantity());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
    
//    public void update(Connection conn, OrderDetails orderDetails) throws SQLException {
//        PreparedStatement ps = null;
//        try {
//            ps = conn.prepareStatement("UPDATE order_details SET quantity=? WHERE id_order_details=?;");            
//            ps.setInt(1, orderDetails.getQuantity());
//            ps.setInt(2, orderDetails.getId_order_details());
//            ps.executeUpdate();
//            
//            if(orderDetails.getFk_order() != null) {
//                OrderDao.getInstance().update(conn, orderDetails.getFk_order());
//            }
//            
//            if(orderDetails.getFk_product() != null) {
//                ProductDao.getInstance().update(conn, orderDetails.getFk_product());
//            }
//        } finally {
//            ResourcesManager.closeResources(null, ps);
//        }
//    }
    
    public void delete(Connection conn, Order order) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM order_details WHERE fk_order=?;");
            ps.setInt(1, order.getId_order());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    public void delete(Connection conn, Product product) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM order_details WHERE fk_product=?;");
            ps.setInt(1, product.getId_product());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    public OrderDetails find(Connection conn, int orderDetailsId) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        OrderDetails orderDetails = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM order_details WHERE id_order_details=?;");
            ps.setInt(1, orderDetailsId);
            rs = ps.executeQuery();
            if(rs.next()) {
                Order order = OrderDao.getInstance().find(conn, rs.getInt("fk_order"));
                Product product = ProductDao.getInstance().find(conn, rs.getInt("fk_product"));
                orderDetails = new OrderDetails(orderDetailsId, order, product, rs.getInt("quantity"));
            } else {
                return orderDetails;
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return orderDetails;
    }
    
    public List<OrderDetails> findAll(Connection conn) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;        
        List<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
        try {
            ps = conn.prepareStatement("SELECT * FROM order_details;");
            rs = ps.executeQuery();
            if(!rs.next()) {
                return orderDetailsList;
            }
            while(rs.next()) {
                Order order = OrderDao.getInstance().find(conn, rs.getInt("fk_order"));
                Product product = ProductDao.getInstance().find(conn, rs.getInt("fk_product"));
                OrderDetails orderDetails = new OrderDetails(rs.getInt("id_order_details"), order, product, rs.getInt("quantity"));
                orderDetailsList.add(orderDetails);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return orderDetailsList;
    }
}
