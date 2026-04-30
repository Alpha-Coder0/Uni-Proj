package com.mycompany.staffmanagementsystem;
public class EmployeeValidator {
    public static boolean validateId(int id) {
        return id > 0;
    }
    public static boolean validateName(String name) {
        return name != null && !name.trim().isEmpty();
    }
    public static boolean validateAge(int age) {
        return age >= 18;
    }
    public static boolean validateAddress(String address) {
        return address != null && !address.trim().isEmpty();
    }
    public static boolean validateRole(String role) {
        return role != null && !role.trim().isEmpty();
    }
    public static boolean validateSalary(double salary) {
        return salary > 0;
    }
}