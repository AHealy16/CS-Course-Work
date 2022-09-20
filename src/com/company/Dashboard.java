package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame{
    private JPanel dashboardPanel;
    private JButton loginBtn;
    private JButton registerBtn;
    private JLabel robbin;

    public Dashboard() {
        setTitle("Dashboard");
        setContentPane(dashboardPanel);
        setMinimumSize(new Dimension(500, 429));
        setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Makes it so when you click the login button the login tab is opened and dashboard is closed
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dashboard.this.dispose();
                Login loginForm = new Login(Dashboard.this);
            }

        });

        //Makes it so when you click the register button the register tab is opened and dashboard is closed
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dashboard.this.dispose();
                CSProjectGUI registrationForm = new CSProjectGUI(Dashboard.this);
                User user = registrationForm.getUser();
            }
        });
     setVisible(true);
    }


}