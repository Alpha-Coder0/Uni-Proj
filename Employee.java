package com.mycompany.staffmanagementsystem;
public class Employee {
    private int id;
    private String name;
    private int age;
    private String address;
    private String role;
    private double salary;
    public Employee() {
    }
    public Employee(int id, String name, int age, String address, String role, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.role = role;
        this.salary = salary;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getAddress() {
        return address;
    }
    public String getRole() {
        return role;
    }
    public double getSalary() {
        return salary;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
}