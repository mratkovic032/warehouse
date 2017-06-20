package com.singidunum.projectKDP.service;

import com.singidunum.projectKDP.dao.ProductDao;
import com.singidunum.projectKDP.dao.ResourcesManager;
import com.singidunum.projectKDP.dao.SupplierDao;
import com.singidunum.projectKDP.data.Product;
import com.singidunum.projectKDP.data.Supplier;
import com.singidunum.projectKDP.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;

public class ProductService {

    private static final ProductService INSTANCE = new ProductService();
    
    private ProductService() {}
    
    public static ProductService getInstance() {
        return INSTANCE;
    }
    
    public void addNewProduct(Product p) throws WarehouseException {
        Connection conn = null;
        try {
            conn = ResourcesManager.getConnection();
            conn.setAutoCommit(false);
            ProductDao.getInstance().insert(conn, p);
            conn.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(conn);
            throw new WarehouseException("Failed to add new Product.");
        } finally {
            ResourcesManager.closeConnection(conn);
        }
    }
    
    public void addNewSupplier(Supplier s) throws WarehouseException {
        Connection conn = null;
        try {
            conn = ResourcesManager.getConnection();
            SupplierDao.getInstance().insert(conn, s);            
        } catch (SQLException ex) {            
            throw new WarehouseException("Failed to add new Supplier.");
        } finally {
            ResourcesManager.closeConnection(conn);
        }
    }
    
    public Product findProduct(int productId) throws WarehouseException {
        Connection conn = null;
        try {
            conn = ResourcesManager.getConnection();
            return ProductDao.getInstance().find(conn, productId);
        } catch (SQLException ex) {
            throw new WarehouseException("Failed to find Product.");
        } finally {
            ResourcesManager.closeConnection(conn);
        }    
    }
    
    public Supplier findSupplier(String supplier_name) throws WarehouseException {
        Connection conn = null;
        try {
            conn = ResourcesManager.getConnection();
            return SupplierDao.getInstance().find(conn, supplier_name);
        } catch (SQLException ex) {
            throw new WarehouseException("Failed to find Product.");
        } finally {
            ResourcesManager.closeConnection(conn);
        }    
    }
    
    public void updateProduct(Product p) throws WarehouseException {
        Connection conn = null;
        try {
            conn = ResourcesManager.getConnection();
            conn.setAutoCommit(false);
            ProductDao.getInstance().update(conn, p);
            conn.commit();            
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(conn);
            throw new WarehouseException("Failed to update Product.");
        } finally {
            ResourcesManager.closeConnection(conn);
        }
    }
    
    public void updateSupplier(Supplier s) throws WarehouseException {
        Connection conn = null;
        try {
            conn = ResourcesManager.getConnection();
            SupplierDao.getInstance().update(conn, s);
        } catch (SQLException ex) {            
            throw new WarehouseException("Failed to update Product.");
        } finally {
            ResourcesManager.closeConnection(conn);
        }
    }
    
    public void deleteProduct(Product product) throws WarehouseException {
        Connection conn = null;
        try {
            conn = ResourcesManager.getConnection();
            conn.setAutoCommit(false);
            ProductDao.getInstance().delete(conn, product);  
            conn.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(conn);
            throw new WarehouseException("Failed to delete Product.");
        } finally {
            ResourcesManager.closeConnection(conn);
        }
    }
    
    public void deleteSupplier(int supplierId) throws WarehouseException {
        Connection conn = null;
        try {
            conn = ResourcesManager.getConnection();
            SupplierDao.getInstance().delete(conn, supplierId);
        } catch (SQLException ex) {
            throw new WarehouseException("Failed to delete Product.");
        } finally {
            ResourcesManager.closeConnection(conn);
        }
    }
}
