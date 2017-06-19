package com.singidunum.projectKDP.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.singidunum.projectKDP.exception.WarehouseException;

public class ResourcesManager {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/warehouse?user=root&password=");
        return conn;
    }

    public static void closeResources(ResultSet resultSet, PreparedStatement preparedStatement) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }

    public static void closeConnection(Connection conn) throws WarehouseException {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                throw new WarehouseException("Failed to close database connection.", ex);
            }
        }
    }

    public static void rollbackTransactions(Connection conn) throws WarehouseException {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new WarehouseException("Failed to rollback database transactions.", ex);
            }
        }
    }
}
