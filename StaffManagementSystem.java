package com.mycompany.staffmanagementsystem;
import javax.swing.SwingUtilities;
import java.util.ArrayList;
public class StaffManagementSystem {
    private ArrayList<Employee> employees = new ArrayList<>();
    public void loadFromDatabase() {
        DataBaseHandler.initDB();
        employees = DataBaseHandler.readDataBase();
    }
    public boolean insertEmployee(Employee e) {
        if (e == null) {
            return false;
        }
        if (!EmployeeValidator.validateId(e.getId()) ||
            !EmployeeValidator.validateName(e.getName()) ||
            !EmployeeValidator.validateAge(e.getAge()) ||
            !EmployeeValidator.validateAddress(e.getAddress()) ||
            !EmployeeValidator.validateRole(e.getRole()) ||
            !EmployeeValidator.validateSalary(e.getSalary())) {
            return false;
        }
        if (searchEmployeeById(e.getId()) != null) {
            return false;
        }
        e.setSalary(SalaryCalculator.calculateSalary(e.getSalary()));
        employees.add(e);
        DataBaseHandler.saveDataBase(e);
        return true;
    }
    public Employee searchEmployeeById(int id) {
        for (Employee e : employees) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }
    public boolean editEmployeeDetails(int id, String name, int age, String address, String role, double salary) {
        Employee e = searchEmployeeById(id);
        if (e == null) {
            return false;
        }
        if (!EmployeeValidator.validateName(name) ||
            !EmployeeValidator.validateAge(age) ||
            !EmployeeValidator.validateAddress(address) ||
            !EmployeeValidator.validateRole(role) ||
            !EmployeeValidator.validateSalary(salary)) {
            return false;
        }
        e.setName(name);
        e.setAge(age);
        e.setAddress(address);
        e.setRole(role);
        e.setSalary(SalaryCalculator.calculateSalary(salary));
        DataBaseHandler.updateDatabse(e);
        return true;
    }
    public boolean deleteEmployee(int id) {
        Employee e = searchEmployeeById(id);
        if (e == null) {
            return false;
        }
        boolean removed = employees.removeIf(emp -> emp.getId() == id);
        if (removed) {
            DataBaseHandler.deleteDataBase(id);
        }
        return removed;
    }
    public ArrayList<Employee> searchEmployeesWithHighWage() {
        ArrayList<Employee> result = new ArrayList<>();
        for (Employee e : employees) {
            if (e.getSalary() > 20000) {
                result.add(e);
            }
        }
        return result;
    }
    public ArrayList<Employee> getAllEmployees() {
        return employees;
    }
    public static void main(String[] args) {
        StaffManagementSystem system = new StaffManagementSystem();
        system.loadFromDatabase();
        SwingUtilities.invokeLater(() -> {
            StaffGui gui = new StaffGui(system);
            gui.displayMainMenu();
        });
    }
}