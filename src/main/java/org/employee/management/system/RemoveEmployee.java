package org.employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class RemoveEmployee extends JFrame {

    private JComboBox<String> empIdBox;
    private JLabel textName, textPhone, textEmail;
    private JButton delete, back;

    public RemoveEmployee() {
        setTitle("Remove Employee");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel label = new JLabel("Employee ID");
        label.setBounds(50, 50, 120, 30);
        label.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(label);

        empIdBox = new JComboBox<>();
        empIdBox.setBounds(200, 50, 150, 30);
        add(empIdBox);

        JLabel labelName = new JLabel("Name");
        labelName.setBounds(50, 100, 100, 30);
        labelName.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(labelName);

        textName = new JLabel();
        textName.setBounds(200, 100, 200, 30);
        add(textName);

        JLabel labelPhone = new JLabel("Phone");
        labelPhone.setBounds(50, 150, 100, 30);
        labelPhone.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(labelPhone);

        textPhone = new JLabel();
        textPhone.setBounds(200, 150, 200, 30);
        add(textPhone);

        JLabel labelEmail = new JLabel("Email");
        labelEmail.setBounds(50, 200, 100, 30);
        labelEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(labelEmail);

        textEmail = new JLabel();
        textEmail.setBounds(200, 200, 200, 30);
        add(textEmail);

        // Buttons
        delete = new JButton("Delete");
        delete.setBounds(80, 300, 100, 30);
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);
        delete.addActionListener(this::deleteEmployee);
        add(delete);

        back = new JButton("Back");
        back.setBounds(220, 300, 100, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(e -> {
            setVisible(false);
            new Main_class();
        });
        add(back);

        // Icons
        JLabel icon = new JLabel(new ImageIcon(
                new ImageIcon(ClassLoader.getSystemResource("icons/delete.png"))
                        .getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
        icon.setBounds(500, 80, 200, 200);
        add(icon);

        JLabel background = new JLabel(new ImageIcon(
                new ImageIcon(ClassLoader.getSystemResource("icons/rback.png"))
                        .getImage().getScaledInstance(1120, 630, Image.SCALE_SMOOTH)));
        background.setBounds(0, 0, 1120, 630);
        add(background);

        loadEmployeeIds();
        empIdBox.addActionListener(e -> loadEmployeeDetails((String) empIdBox.getSelectedItem()));

        setVisible(true);
    }

    private void loadEmployeeIds() {
        try (conn c = new conn()) {
            ResultSet rs = c.statement.executeQuery("SELECT empId FROM employee");
            empIdBox.removeAllItems();
            while (rs.next()) {
                empIdBox.addItem(rs.getString("empId"));
            }
            if (empIdBox.getItemCount() > 0) {
                loadEmployeeDetails((String) empIdBox.getSelectedItem());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadEmployeeDetails(String empId) {
        try (conn c = new Conn()) {
            ResultSet rs = c.statement.executeQuery("SELECT name, phone, email FROM employee WHERE empId = '" + empId + "'");
            if (rs.next()) {
                textName.setText(rs.getString("name"));
                textPhone.setText(rs.getString("phone"));
                textEmail.setText(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteEmployee(ActionEvent e) {
        String empId = (String) empIdBox.getSelectedItem();
        if (empId == null) {
            JOptionPane.showMessageDialog(this, "No employee selected!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete employee " + empId + "?",
                "Confirm Deletion", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try (conn c = new conn()) {
                String query = "DELETE FROM employee WHERE empId = '" + empId + "'";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "Employee Deleted Successfully");
                loadEmployeeIds();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new RemoveEmployee();
    }
}
