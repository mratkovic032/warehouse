package com.singidunum.projectKDP.service;

import com.singidunum.projectKDP.dao.OrderDao;
import com.singidunum.projectKDP.dao.OrderDetailsDao;
import com.singidunum.projectKDP.dao.ResourcesManager;
import com.singidunum.projectKDP.data.Customer;
import com.singidunum.projectKDP.data.Employee;
import com.singidunum.projectKDP.data.Order;
import com.singidunum.projectKDP.data.OrderDetails;
import com.singidunum.projectKDP.data.Product;
import com.singidunum.projectKDP.data.Shipper;
import com.singidunum.projectKDP.exception.WarehouseException;
import java.sql.Connection;
import java.sql.Date;

public class OrderService {

    private static final OrderService INSTANCE = new OrderService();
    
    private OrderService() {}
    
    public static OrderService getInstance() {
        return INSTANCE;
    }
    
    public void makeOrder(Date date, Customer c, Employee e, Shipper s, Product p, int quantity) throws WarehouseException {
        Connection conn = null;
        try {
            conn = ResourcesManager.getConnection();
            conn.setAutoCommit(false);
            
            Order order = new Order(date, c, e, s);
            System.out.println(order);
            OrderDao.getInstance().insert(conn, order);

            OrderDetails orderDetails = new OrderDetails(order, p, quantity);
            OrderDetailsDao.getInstance().insert(conn, orderDetails);
            
            conn.commit();
        } catch (Exception ex) {            
            ResourcesManager.rollbackTransactions(conn);
            System.out.println(ex);
            throw new WarehouseException("Failed to make Order.");
        } finally {
            ResourcesManager.closeConnection(conn);
        }
    }    
}
