package org.employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateEmployee extends JFrame implements ActionListener {

    JTextField tname, tsalary, taddress, tphone, temail;
    JLabel tempid, tdob, taadhar;
    JButton add, back;
    JComboBox<String> teducation, tdesignation;

    String number;

    UpdateEmployee(String number) {
        this.number = number;

        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setSize(900, 600);
        setLocation(300, 100);

        JLabel heading = new JLabel("Update Employee Details");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("Tahoma", Font.BOLD, 25));
        add(heading);

        // Labels
        addLabel("Name", 50, 150);
        tname = new JTextField();
        tname.setBounds(200, 150, 150, 30);
        add(tname);

        addLabel("Date of Birth", 400, 150);
        tdob = new JLabel();
        tdob.setBounds(600, 150, 150, 30);
        add(tdob);

        addLabel("Salary", 50, 200);
        tsalary = new JTextField();
        tsalary.setBounds(200, 200, 150, 30);
        add(tsalary);

        addLabel("Address", 400, 200);
        taddress = new JTextField();
        taddress.setBounds(600, 200, 150, 30);
        add(taddress);

        addLabel("Phone", 50, 250);
        tphone = new JTextField();
        tphone.setBounds(200, 250, 150, 30);
        add(tphone);

        addLabel("Email", 400, 250);
        temail = new JTextField();
        temail.setBounds(600, 250, 150, 30);
        add(temail);

        addLabel("Highest Education", 50, 300);
        String[] courses = { "BBA", "BCA", "BA", "B.COM", "BSC", "BTECH", "MBA", "MCA", "MA", "MCOM", "MSC", "PHD" };
        teducation = new JComboBox<>(courses);
        teducation.setBackground(Color.WHITE);
        teducation.setBounds(200, 300, 150, 30);
        add(teducation);

        addLabel("Designation", 400, 300);
        String[] designations = { "Software Engineer", "Senior Software Engineer", "Manager", "Team Lead", "HR" };
        tdesignation = new JComboBox<>(designations);
        tdesignation.setBackground(Color.WHITE);
        tdesignation.setBounds(600, 300, 150, 30);
        add(tdesignation);

        addLabel("Aadhar Number", 50, 350);
        taadhar = new JLabel();
        taadhar.setBounds(200, 350, 150, 30);
        add(taadhar);

        addLabel("Employee Id", 400, 350);
        tempid = new JLabel();
        tempid.setBounds(600, 350, 150, 30);
        add(tempid);

        // Fetch Data from Database
        try {
            Conn c = new Conn();
            String query = "SELECT * FROM employee WHERE empId=?";
            PreparedStatement pstmt = c.connection.prepareStatement(query);
            pstmt.setString(1, number);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                tname.setText(resultSet.getString("fname"));
                tdob.setText(resultSet.getString("dob"));
                tsalary.setText(resultSet.getString("salary"));
                taddress.setText(resultSet.getString("address"));
                tphone.setText(resultSet.getString("phone"));
                temail.setText(resultSet.getString("email"));
                teducation.setSelectedItem(resultSet.getString("education"));
                tdesignation.setSelectedItem(resultSet.getString("designation"));
                taadhar.setText(resultSet.getString("aadhar")); // Fixed spelling
                tempid.setText(resultSet.getString("empId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Buttons
        add = new JButton("Update Details");
        add.setBounds(250, 450, 150, 40);
        add.addActionListener(this);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add(add);

        back = new JButton("Back");
        back.setBounds(450, 450, 150, 40);
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        add(back);

        setVisible(true);
    }

    // Helper method for labels
    private void addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 150, 30);
        label.setFont(new Font("serif", Font.PLAIN, 20));
        add(label);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            updateEmployee();
        } else {
            setVisible(false);
            new Home();
        }
    }

    private void updateEmployee() {
        String fname = tname.getText();
        String salary = tsalary.getText();
        String address = taddress.getText();
        String phone = tphone.getText();
        String email = temail.getText();
        String education = (String) teducation.getSelectedItem();
        String designation = (String) tdesignation.getSelectedItem();

        try {
            Conn c = new Conn();
            String query = "UPDATE employee SET fname=?, salary=?, address=?, phone=?, email=?, education=?, designation=? WHERE empId=?";
            PreparedStatement pstmt = c.connection.prepareStatement(query);
            pstmt.setString(1, fname);
            pstmt.setString(2, salary);
            pstmt.setString(3, address);
            pstmt.setString(4, phone);
            pstmt.setString(5, email);
            pstmt.setString(6, education);
            pstmt.setString(7, designation);
            pstmt.setString(8, number);

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Details Updated Successfully");
            setVisible(false);
            new Home();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new UpdateEmployee("1"); // Example employee ID
    }
}