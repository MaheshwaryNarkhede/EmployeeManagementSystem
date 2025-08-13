package org.employee.management.system;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main_class extends JFrame {

    public Main_class() {
        setTitle("Employee Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1120, 630);
        setLocationRelativeTo(null);
        setLayout(null);

        // Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/home.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1120, 630, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(i2));
        background.setBounds(0, 0, 1120, 630);
        add(background);

        // Heading
        JLabel heading = new JLabel("Employee Management System", SwingConstants.CENTER);
        heading.setBounds(0, 120, 1120, 40);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 28));
        heading.setForeground(Color.WHITE);
        background.add(heading);

        // Button styles
        Color btnColor = new Color(30, 144, 255);
        Font btnFont = new Font("Segoe UI", Font.BOLD, 14);

        JButton addBtn = createButton("Add Employee", 335, 270, btnColor, btnFont);
        addBtn.addActionListener(e -> {
            new AddEmployee();
            setVisible(false);
        });
        background.add(addBtn);

        JButton viewBtn = createButton("View Employee", 565, 270, btnColor, btnFont);
        viewBtn.addActionListener(e -> {
            new View_Employee();
            setVisible(false);
        });
        background.add(viewBtn);

        JButton removeBtn = createButton("Remove Employee", 450, 370, new Color(220, 20, 60), btnFont);
        removeBtn.addActionListener(e -> {
            new RemoveEmployee();
            setVisible(false);
        });
        background.add(removeBtn);

        setVisible(true);
    }

    private JButton createButton(String text, int x, int y, Color bgColor, Font font) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 150, 40);
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFont(font);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder());
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main_class::new);
    }
}
