package com.singidunum.projectKDP.service;

import com.singidunum.projectKDP.dao.CustomerDao;
import com.singidunum.projectKDP.dao.EmployeeDao;
import com.singidunum.projectKDP.dao.ResourcesManager;
import com.singidunum.projectKDP.dao.ShipperDao;
import com.singidunum.projectKDP.data.Customer;
import com.singidunum.projectKDP.data.Employee;
import com.singidunum.projectKDP.data.Shipper;
import com.singidunum.projectKDP.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;

public class CustomerService {

    private static final CustomerService INSTANCE = new CustomerService();
    
    private CustomerService() {}
    
    public static CustomerService getInstance() {
        return INSTANCE;
    }
    
    public void addNewCustomer(Customer c) throws SQLException, WarehouseException {
        Connection conn = null;
        try {
            conn = ResourcesManager.getConnection();
            CustomerDao.getInstance().insert(conn, c);
        } catch (SQLException ex) {
            throw new WarehouseException("Failed to add Customer.");
        } finally {
            ResourcesManager.closeConnection(conn);
        }
    }
    
    public void addNewEmployee(Employee e) throws SQLException, WarehouseException {
        Connection conn = null;
        try {
            conn = ResourcesManager.getConnection();
            EmployeeDao.getInstance().insert(conn, e);
        } catch (SQLException ex) {
            throw new WarehouseException("Failed to add Employee.");
        } finally {
            ResourcesManager.closeConnection(conn);
        }
    }
    
    public void addNewShipper(Shipper s) throws SQLException, WarehouseException {
        Connection conn = null;
        try {
            conn = ResourcesManager.getConnection();
            ShipperDao.getInstance().insert(conn, s);
        } catch (SQLException ex) {
            throw new WarehouseException("Failed to add Shipper.");
        } finally {
            ResourcesManager.closeConnection(conn);
        }
    }
    
    public Customer findCustomer(int customerId) throws WarehouseException {
        Connection conn = null;
        try {
            conn = ResourcesManager.getConnection();
            return CustomerDao.getInstance().find(conn, customerId);
        } catch (SQLException ex) {
            throw new WarehouseException("Failed to find Customer.");
        } finally {
            ResourcesManager.closeConnection(conn);
        }
    }
    
    public Employee findEmployee(int employeeId) throws WarehouseException {
        Connection conn = null;
        try {
            conn = ResourcesManager.getConnection();
            return EmployeeDao.getInstance().find(conn, employeeId);
        } catch (SQLException ex) {
            throw new WarehouseException("Failed to find Employee.");
        } finally {
            ResourcesManager.closeConnection(conn);
        }
    }
    
    public Shipper findShipper(int shipperId) throws WarehouseException {
        Connection conn = null;
        try {
            conn = ResourcesManager.getConnection();
            return ShipperDao.getInstance().find(conn, shipperId);
        } catch (SQLException ex) {
            throw new WarehouseException("Failed to find Shipper.");
        } finally {
            ResourcesManager.closeConnection(conn);
        }
    }
    
    public void updateCustomer(Customer c) throws WarehouseException {
        Connection conn = null;
        try {
            conn = ResourcesManager.getConnection();
            CustomerDao.getInstance().update(conn, c);
        } catch (SQLException ex) {
            throw new WarehouseException("Failed to update Product.");
        } finally {
            ResourcesManager.closeConnection(conn);
        }
    }
    
    public void updateEmployee(Employee e) throws WarehouseException {
        Connection conn = null;
        try {
            conn = ResourcesManager.getConnection();
            EmployeeDao.getInstance().update(conn, e);
        } catch (SQLException ex) {
            throw new WarehouseException("Failed to update Employee.");
        } finally {
            ResourcesManager.closeConnection(conn);
        }
    }
    
    public void updateShipper(Shipper s) throws WarehouseException {
        Connection conn = null;
        try {
            conn = ResourcesManager.getConnection();
            ShipperDao.getInstance().update(conn, s);
        } catch (SQLException ex) {
            throw new WarehouseException("Failed to update Shipper.");
        } finally {
            ResourcesManager.closeConnection(conn);
        }
    }
    
    public void deleteCustomer(int customerId) throws WarehouseException {
        Connection conn = null;
        try {
            conn = ResourcesManager.getConnection();
            CustomerDao.getInstance().delete(conn, customerId);
        } catch (SQLException ex) {
            throw new WarehouseException("Failed to delete Customer.");
        } finally {
            ResourcesManager.closeConnection(conn);
        }
    }
    
    public void deleteEmployee(int emoloyeeId) throws WarehouseException {
        Connection conn = null;
        try {
            conn = ResourcesManager.getConnection();
            EmployeeDao.getInstance().delete(conn, emoloyeeId);
        } catch (SQLException ex) {
            throw new WarehouseException("Failed to delete Employee.");
        } finally {
            ResourcesManager.closeConnection(conn);
        }
    }
    
    public void deleteShipper(int shipperId) throws WarehouseException {
        Connection conn = null;
        try {
            conn = ResourcesManager.getConnection();
            ShipperDao.getInstance().delete(conn, shipperId);
        } catch (SQLException ex) {
            throw new WarehouseException("Failed to delete Shipper.");
        } finally {
            ResourcesManager.closeConnection(conn);
        }
    }
}
