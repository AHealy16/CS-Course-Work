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

        //Uses and ActionListener to know when you hit the button and then closes the Dashboard tab and opens the Login tab
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dashboard.this.dispose();
                Login loginForm = new Login(Dashboard.this);
            }
        });

        //Uses and ActionListener to know when you hit the button and then closes the Dashboard tab and opens the Registration tab
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