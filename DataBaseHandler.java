package com.mycompany.staffmanagementsystem;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseHandler {

private static final String URL = "jdbc:sqlite:staff_management.db";

 public static void initDB() {
    System.out.println("Initializing database...");

    String sql = "CREATE TABLE IF NOT EXISTS employees ("
            + "id INTEGER PRIMARY KEY, "
            + "name TEXT NOT NULL, "
            + "age INTEGER NOT NULL, "
            + "address TEXT NOT NULL, "
            + "role TEXT NOT NULL, "
            + "salary REAL NOT NULL"
            + ")";

    try (Connection conn = DriverManager.getConnection(URL);
         Statement stmt = conn.createStatement()) {

        stmt.execute(sql);
        System.out.println("Database created successfully!");

    } catch (Exception e) {
        System.out.println("Database ERROR:");
        e.printStackTrace();
    }
}

    public static void saveDataBase(Employee e) {
        String sql = "INSERT INTO employees (id, name, age, address, role, salary) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, e.getId());
            ps.setString(2, e.getName());
            ps.setInt(3, e.getAge());
            ps.setString(4, e.getAddress());
            ps.setString(5, e.getRole());
            ps.setDouble(6, e.getSalary());

            int rows = ps.executeUpdate();
            System.out.println("Inserted rows: " + rows);

        } catch (Exception ex) {
            System.out.println("saveDataBase failed");
            ex.printStackTrace();
        }
    }

    public static ArrayList<Employee> readDataBase() {
        ArrayList<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees ORDER BY id";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                employees.add(new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("address"),
                        rs.getString("role"),
                        rs.getDouble("salary")
                ));
            }

        } catch (Exception e) {
            System.out.println("readDataBase failed");
            e.printStackTrace();
        }

        return employees;
    }

    public static void updateDatabse(Employee e) {
        String sql = "UPDATE employees SET name=?, age=?, address=?, role=?, salary=? WHERE id=?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, e.getName());
            ps.setInt(2, e.getAge());
            ps.setString(3, e.getAddress());
            ps.setString(4, e.getRole());
            ps.setDouble(5, e.getSalary());
            ps.setInt(6, e.getId());

            int rows = ps.executeUpdate();
            System.out.println("Updated rows: " + rows);

        } catch (Exception ex) {
            System.out.println("updateDatabse failed");
            ex.printStackTrace();
        }
    }

    public static void deleteDataBase(int id) {
        String sql = "DELETE FROM employees WHERE id=?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println("Deleted rows: " + rows);

        } catch (Exception e) {
            System.out.println("deleteDataBase failed");
            e.printStackTrace();
        }
    }
}
