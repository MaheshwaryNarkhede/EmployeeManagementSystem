package org.employee.management.system;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AddEmployee extends JFrame implements ActionListener {

    Random ran = new Random();
    int number = ran.nextInt(900000) + 100000; // 6-digit employee ID

    JTextField tname, tfname, taddress, tphone, taadhar, temail, tsalary, tdesignation;
    JLabel tempid;
    JDateChooser tdob;
    JButton addBtn, backBtn;
    JComboBox<String> educationBox;

    public AddEmployee() {
        setTitle("Add Employee");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 720);
        setLocationRelativeTo(null); // Center window
        setLayout(null);

        getContentPane().setBackground(new Color(230, 248, 255));

        JLabel heading = new JLabel("New Employee Registration");
        heading.setBounds(280, 20, 400, 40);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 26));
        heading.setForeground(new Color(40, 40, 40));
        add(heading);

        int labelX = 50, textX = 200, labelWidth = 140, height = 30, gapY = 50;
        int row = 0;

        addLabel("Name:", labelX, 100 + row * gapY, labelWidth, height);
        tname = addTextField(textX, 100 + row++ * gapY);

        addLabel("Father's Name:", 450, 100, labelWidth + 30, height);
        tfname = addTextField(600, 100);

        addLabel("Date of Birth:", labelX, 100 + row * gapY, labelWidth, height);
        tdob = new JDateChooser();
        tdob.setBounds(textX, 100 + row * gapY, 180, height);
        tdob.setBackground(Color.WHITE);
        add(tdob);

        addLabel("Salary:", 450, 150, labelWidth, height);
        tsalary = addTextField(600, 150);

        row++;
        addLabel("Address:", labelX, 100 + row * gapY, labelWidth, height);
        taddress = addTextField(textX, 100 + row * gapY);

        addLabel("Phone:", 450, 200, labelWidth, height);
        tphone = addTextField(600, 200);

        row++;
        addLabel("Email:", labelX, 100 + row * gapY, labelWidth, height);
        temail = addTextField(textX, 100 + row * gapY);

        addLabel("Education:", 450, 250, labelWidth, height);
        String[] items = {"BBA", "B.Tech", "BCA", "BA", "BSC", "B.COM", "MBA", "MCA", "MA", "MTech", "MSC", "PhD"};
        educationBox = new JComboBox<>(items);
        educationBox.setBounds(600, 250, 180, height);
        educationBox.setBackground(Color.WHITE);
        add(educationBox);

        row++;
        addLabel("Designation:", labelX, 100 + row * gapY, labelWidth, height);
        tdesignation = addTextField(textX, 100 + row * gapY);

        addLabel("Aadhar No:", 450, 300, labelWidth, height);
        taadhar = addTextField(600, 300);

        row++;
        addLabel("Employee ID:", labelX, 100 + row * gapY, labelWidth, height);
        tempid = new JLabel(String.valueOf(number));
        tempid.setBounds(textX, 100 + row * gapY, 180, height);
        tempid.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tempid.setForeground(new Color(200, 0, 0));
        add(tempid);

        // Buttons
        addBtn = createButton("Add Employee", 250, 560);
        addBtn.addActionListener(this);

        backBtn = createButton("Back", 450, 560);
        backBtn.addActionListener(this);

        setVisible(true);
    }

    private void addLabel(String text, int x, int y, int w, int h) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, w, h);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        add(label);
    }

    private JTextField addTextField(int x, int y) {
        JTextField tf = new JTextField();
        tf.setBounds(x, y, 180, 30);
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tf.setBackground(Color.WHITE);
        add(tf);
        return tf;
    }

    private JButton createButton(String text, int x, int y) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 150, 40);
        btn.setBackground(new Color(30, 144, 255));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        add(btn);
        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBtn) {
            String name = tname.getText();
            String fname = tfname.getText();
            String dob = ((JTextField) tdob.getDateEditor().getUiComponent()).getText();
            String salary = tsalary.getText();
            String address = taddress.getText();
            String phone = tphone.getText();
            String email = temail.getText();
            String education = (String) educationBox.getSelectedItem();
            String designation = tdesignation.getText();
            String aadhar = taadhar.getText();
            String empID = tempid.getText();

            try {
                conn c = new conn();
                String query = "INSERT INTO employee VALUES('" + name + "', '" + fname + "', '" + dob + "', '" + salary +
                        "', '" + address + "', '" + phone + "', '" + email + "', '" + education + "', '" + designation +
                        "', '" + aadhar + "', '" + empID + "')";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Employee added successfully!");
                setVisible(false);
                new Main_class();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == backBtn) {
            setVisible(false);
            new Main_class();
        }
    }

    public static void main(String[] args) {
        new AddEmployee();
    }
}
