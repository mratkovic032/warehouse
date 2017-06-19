package com.singidunum.projectKDP.dao;

import com.singidunum.projectKDP.data.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    private static final EmployeeDao INSTANCE = new EmployeeDao();
    
    private EmployeeDao() {}
    
    public static EmployeeDao getInstance() {
        return INSTANCE;
    }
    
    public int insert(Connection conn, Employee employee) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = conn.prepareStatement("INSERT INTO employee(last_name, first_name, birth_date) VALUES(?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, employee.getLast_name());
            ps.setString(2, employee.getFirst_name());
            ps.setDate(3, employee.getBirth_date());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(null, ps);
        }              
        return id;
    }
    
    public void update(Connection conn, Employee employee) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("UPDATE employee SET last_name=?, first_name=?, birth_date=? WHERE id_employee=?;");
            ps.setString(1, employee.getLast_name());
            ps.setString(2, employee.getFirst_name());
            ps.setDate(3, employee.getBirth_date());
            ps.setInt(4, employee.getId_employee());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }                
    }
    
    public void delete(Connection conn, int employeeId) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM employee WHERE id_employee=?;");
            ps.setInt(1, employeeId);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    public Employee find(Connection conn, int employeeId) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Employee employee = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM employee WHERE id_employee=?;");
            ps.setInt(1, employeeId);
            rs = ps.executeQuery();
            if(rs.next()) {
                employee = new Employee(rs.getInt("id_employee"), rs.getString("last_name"), rs.getString("first_name"), rs.getDate("birth_date"));
            } else {
                return employee;
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return employee;
    }
    
    public List<Employee> findAll(Connection conn) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;         
        List<Employee> employeeList = new ArrayList<Employee>();
        try {
            ps = conn.prepareStatement("SELECT * FROM employee;");
            rs = ps.executeQuery();
            if(!rs.next()) {
                return employeeList;
            }
            while(rs.next()) {
                Employee employee = new Employee(rs.getInt("id_employee"), rs.getString("last_name"), rs.getString("first_name"), rs.getDate("birth_date"));
                employeeList.add(employee);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return employeeList;
    }
}
