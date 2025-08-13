package org.employee.management.system;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    private JTextField tusername;
    private JPasswordField tpassword;
    private JButton loginBtn, backBtn;

    public Login() {
        setTitle("Employee Management System - Login");
        setLayout(null);
        setSize(650, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(new Color(240, 248, 255));

        JLabel heading = new JLabel("Login");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 26));
        heading.setBounds(250, 20, 200, 40);
        add(heading);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(80, 90, 100, 30);
        usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        add(usernameLabel);

        tusername = new JTextField();
        tusername.setBounds(180, 90, 180, 30);
        tusername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        add(tusername);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(80, 140, 100, 30);
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        add(passwordLabel);

        tpassword = new JPasswordField();
        tpassword.setBounds(180, 140, 180, 30);
        tpassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        add(tpassword);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(180, 200, 85, 35);
        loginBtn.setBackground(new Color(30, 144, 255));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginBtn.setFocusPainted(false);
        loginBtn.addActionListener(this);
        add(loginBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(275, 200, 85, 35);
        backBtn.setBackground(new Color(220, 20, 60));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backBtn.setFocusPainted(false);
        backBtn.addActionListener(this);
        add(backBtn);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        JLabel imgLabel = new JLabel(new ImageIcon(i2));
        imgLabel.setBounds(400, 50, 200, 200);
        add(imgLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginBtn) {
            String username = tusername.getText().trim();
            String password = new String(tpassword.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both username and password.");
                return;
            }

            try {
                Conn db = new Conn();
                String sql = "SELECT * FROM login WHERE username = ? AND password = ?";
                PreparedStatement ps = db.getConnection().prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    setVisible(false);
                    new Main_class();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password.");
                }

                rs.close();
                ps.close();
                db.close();

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
            }

        } else if (e.getSource() == backBtn) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
