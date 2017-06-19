package com.singidunum.projectKDP.data;

import java.sql.Date;

public class Employee {

    private int id_employee;
    private String last_name;
    private String first_name;
    private Date birth_date;

    public Employee(int id_employee, String last_name, String first_name, Date birth_date) {
        this.id_employee = id_employee;
        this.last_name = last_name;
        this.first_name = first_name;
        this.birth_date = birth_date;
    }

    public Employee(String last_name, String first_name, Date birth_date) {
        this.last_name = last_name;
        this.first_name = first_name;
        this.birth_date = birth_date;
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }
    
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id_employee).append(": ").append(last_name).append(" ").append(first_name).append("(").append(birth_date).append(")");
        return sb.toString();
    }
}
