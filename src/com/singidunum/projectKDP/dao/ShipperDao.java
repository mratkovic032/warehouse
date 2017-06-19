package com.singidunum.projectKDP.dao;

import com.singidunum.projectKDP.data.Shipper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShipperDao {

    private static final ShipperDao INSTANCE = new ShipperDao();
    
    private ShipperDao() {}
    
    public static ShipperDao getInstance() {
        return INSTANCE;
    }
    
    public void insert(Connection conn, Shipper shipper) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("INSERT INTO shipper(shipper_name, phone) VALUES(?, ?);", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, shipper.getShipper_name());
            ps.setString(2, shipper.getPhone());
            ps.executeUpdate();;
        } finally {
            ResourcesManager.closeResources(null, ps);
        }       
    }
    
    public void update(Connection conn, Shipper shipper) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("UPDATE shipper SET shipper_name=?, phone=? WHERE id_shipper=?;");
            ps.setString(1, shipper.getShipper_name());
            ps.setString(2, shipper.getPhone());
            ps.setInt(3, shipper.getId_shipper());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }                
    }
    
    public void delete(Connection conn, int shipperId) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM shipper WHERE id_shipper=?;");
            ps.setInt(1, shipperId);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }                
    }
    
    public Shipper find(Connection conn, int shipperId) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Shipper shipper = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM shipper WHERE id_shipper=?;");
            ps.setInt(1, shipperId);
            rs = ps.executeQuery();
            if(rs.next()) {
                shipper = new Shipper(shipperId, rs.getString("shipper_name"), rs.getString("phone"));
            } else {
                return shipper;
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return shipper;
    }
    
    public List<Shipper> findAll(Connection conn) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Shipper> shippers = new ArrayList<Shipper>();
        try {
            ps = conn.prepareStatement("SELECT * FROM shipper;");
            rs = ps.executeQuery();
            if(!rs.next()) {
                return shippers;
            }
            while(rs.next()) {
                Shipper shipper = new Shipper(rs.getInt("id_shipper"), rs.getString("shipper_name"), rs.getString("phone"));
                shippers.add(shipper);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return shippers;
    }
}
