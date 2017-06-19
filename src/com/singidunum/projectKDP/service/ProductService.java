package com.singidunum.projectKDP.service;

import com.singidunum.projectKDP.dao.ProductDao;
import com.singidunum.projectKDP.dao.ResourcesManager;
import com.singidunum.projectKDP.dao.ShipperDao;
import com.singidunum.projectKDP.dao.SupplierDao;
import com.singidunum.projectKDP.data.Product;
import com.singidunum.projectKDP.data.Shipper;
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
    
    public void addNewShipper(Shipper s) throws WarehouseException {
        Connection conn = null;
        try {
            conn = ResourcesManager.getConnection();
            ShipperDao.getInstance().insert(conn, s);
        } catch (SQLException ex) {
            throw new WarehouseException("Failed to add new Shipper.");
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
    
    public Supplier findSupplier(int supplierId) throws WarehouseException {
        Connection conn = null;
        try {
            conn = ResourcesManager.getConnection();
            return SupplierDao.getInstance().find(conn, supplierId);
        } catch (SQLException ex) {
            throw new WarehouseException("Failed to find Supplier.");
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
    
    public void deleteProduct(int productId) throws WarehouseException {
        Connection conn = null;
        try {
            conn = ResourcesManager.getConnection();
            ProductDao.getInstance().delete(conn, productId);          
        } catch (SQLException ex) {
            throw new WarehouseException("Failed to delete Product.");
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
