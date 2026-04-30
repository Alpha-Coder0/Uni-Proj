package com.mycompany.staffmanagementsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class StaffGui {
    private final StaffManagementSystem system;

    private JFrame frame;
    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField addressField;
    private JTextField roleField;
    private JTextField salaryField;
    private JTextField searchField;

    private JTable table;
    private DefaultTableModel tableModel;

    private JButton insertButton;
    private JButton searchButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton showAllButton;
    private JButton highWageButton;
    private JButton clearButton;
    public StaffGui(StaffManagementSystem system) {
        this.system = system;
    }
    public void displayMainMenu() {
        frame = new JFrame("Staff Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 700);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));
        JPanel formPanel = buildFormPanel();
        JPanel searchPanel = buildSearchPanel();
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(searchPanel, BorderLayout.SOUTH);
        JPanel buttonPanel = buildButtonPanel();
        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Name", "Age", "Address", "Role", "Salary"},
                0
        );
        table = new JTable(tableModel);
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(new JScrollPane(table), BorderLayout.SOUTH);
        getUserChoice();
        displayRecordList(system.getAllEmployees());
        frame.setVisible(true);
    }
    private JPanel buildFormPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 4, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Employee Details"));
        idField = new JTextField();
        nameField = new JTextField();
        ageField = new JTextField();
        addressField = new JTextField();
        roleField = new JTextField();
        salaryField = new JTextField();
        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);
        panel.add(new JLabel("Address:"));
        panel.add(addressField);
        panel.add(new JLabel("Role:"));
        panel.add(roleField);
        panel.add(new JLabel("Salary:"));
        panel.add(salaryField);
        return panel;
    }
    private JPanel buildSearchPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder("Search / Delete by ID"));
        searchField = new JTextField(15);
        panel.add(new JLabel("Employee ID:"));
        panel.add(searchField);
        return panel;
    }
    private JPanel buildButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 10));
        insertButton = new JButton("Insert");
        searchButton = new JButton("Search");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        showAllButton = new JButton("Show All");
        highWageButton = new JButton("Salary > 20k");
        clearButton = new JButton("Clear");
        panel.add(insertButton);
        panel.add(searchButton);
        panel.add(editButton);
        panel.add(deleteButton);
        panel.add(showAllButton);
        panel.add(highWageButton);
        panel.add(clearButton);
        return panel;
    }
    public void getUserChoice() {
        insertButton.addActionListener(e -> insertEmployeeFromForm());
        searchButton.addActionListener(e -> searchEmployeeFromForm());
        editButton.addActionListener(e -> editEmployeeFromForm());
        deleteButton.addActionListener(e -> deleteEmployeeFromForm());
        showAllButton.addActionListener(e -> displayRecordList(system.getAllEmployees()));
        highWageButton.addActionListener(e -> displayRecordList(system.searchEmployeesWithHighWage()));
        clearButton.addActionListener(e -> clearFields());
    }
    private void insertEmployeeFromForm() {
        try {
            Employee e = readEmployeeFromForm(true);
            if (e == null) {
                return;
            }
            boolean success = system.insertEmployee(e);
            if (success) {
                JOptionPane.showMessageDialog(frame, "Employee inserted successfully.");
                displayRecordList(system.getAllEmployees());
                clearFields();
            } else {
                JOptionPane.showMessageDialog(frame, "Insert failed. Check validation or duplicate ID.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid numeric input.");
        }
    }
    private void searchEmployeeFromForm() {
        try {
            int id = getSearchId();
            if (id == -1) {
                return;
            }
            Employee e = system.searchEmployeeById(id);
            if (e != null) {
                populateFields(e);
                displayRecordList(singleEmployeeList(e));
                JOptionPane.showMessageDialog(frame, "Employee found.");
            } else {
                JOptionPane.showMessageDialog(frame, "Employee not found.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid ID.");
        }
    }
    private void editEmployeeFromForm() {
        try {
            String idText = idField.getText().trim();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Enter the employee ID in the ID field.");
                return;
            }
            int id = Integer.parseInt(idText);
            String name = nameField.getText().trim();
            int age = Integer.parseInt(ageField.getText().trim());
            String address = addressField.getText().trim();
            String role = roleField.getText().trim();
            double salary = Double.parseDouble(salaryField.getText().trim());
            boolean success = system.editEmployeeDetails(id, name, age, address, role, salary);
            if (success) {
                JOptionPane.showMessageDialog(frame, "Employee updated successfully.");
                displayRecordList(system.getAllEmployees());
                clearFields();
            } else {
                JOptionPane.showMessageDialog(frame, "Update failed. Check validation or ID.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid numeric input.");
        }
    }
    private void deleteEmployeeFromForm() {
        try {
            int id = getSearchId();
            if (id == -1) {
                return;
            }
            boolean success = system.deleteEmployee(id);
            if (success) {
                JOptionPane.showMessageDialog(frame, "Employee deleted successfully.");
                displayRecordList(system.getAllEmployees());
                clearFields();
            } else {
                JOptionPane.showMessageDialog(frame, "Employee not found.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid ID.");
        }
    }
    private int getSearchId() {
        String text = searchField.getText().trim();
        if (text.isEmpty()) {
            text = idField.getText().trim();
        }
        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Enter an ID first.");
            return -1;
        }
        return Integer.parseInt(text);
    }
    private Employee readEmployeeFromForm(boolean validateBeforeCreate) {
        int id = Integer.parseInt(idField.getText().trim());
        String name = nameField.getText().trim();
        int age = Integer.parseInt(ageField.getText().trim());
        String address = addressField.getText().trim();
        String role = roleField.getText().trim();
        double salary = Double.parseDouble(salaryField.getText().trim());
        if (validateBeforeCreate) {
            if (!EmployeeValidator.validateId(id)) {
                JOptionPane.showMessageDialog(frame, "Invalid ID.");
                return null;
            }
            if (!EmployeeValidator.validateName(name)) {
                JOptionPane.showMessageDialog(frame, "Invalid Name.");
                return null;
            }
            if (!EmployeeValidator.validateAge(age)) {
                JOptionPane.showMessageDialog(frame, "Invalid Age. Must be 18 or older.");
                return null;
            }
            if (!EmployeeValidator.validateAddress(address)) {
                JOptionPane.showMessageDialog(frame, "Invalid Address.");
                return null;
            }
            if (!EmployeeValidator.validateRole(role)) {
                JOptionPane.showMessageDialog(frame, "Invalid Role.");
                return null;
            }
            if (!EmployeeValidator.validateSalary(salary)) {
                JOptionPane.showMessageDialog(frame, "Invalid Salary.");
                return null;
            }
        }
        Employee e = new Employee();
        e.setId(id);
        e.setName(name);
        e.setAge(age);
        e.setAddress(address);
        e.setRole(role);
        e.setSalary(salary);
        return e;
    }
    public void displayRecordList(ArrayList<Employee> list) {
        tableModel.setRowCount(0);
        for (Employee e : list) {
            tableModel.addRow(new Object[]{
                    e.getId(),
                    e.getName(),
                    e.getAge(),
                    e.getAddress(),
                    e.getRole(),
                    e.getSalary()
            });
        }
    }
    private void populateFields(Employee e) {
        idField.setText(String.valueOf(e.getId()));
        nameField.setText(e.getName());
        ageField.setText(String.valueOf(e.getAge()));
        addressField.setText(e.getAddress());
        roleField.setText(e.getRole());
        salaryField.setText(String.valueOf(e.getSalary()));
    }
    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        addressField.setText("");
        roleField.setText("");
        salaryField.setText("");
        searchField.setText("");
    }
    private ArrayList<Employee> singleEmployeeList(Employee e) {
        ArrayList<Employee> list = new ArrayList<>();
        list.add(e);
        return list;
    }
}