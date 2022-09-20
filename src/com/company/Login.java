package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JDialog {
    private JPasswordField passwordPF;
    private JTextField usernameTF;
    private JButton loginBtn;
    private JButton cancelBtn;
    private JPanel loginPanel;
    private User user;

    public Login(JFrame parent) {
        super(parent);
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //makes the login button work and records the values entered into Strings
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = usernameTF.getText();
                String password = String.valueOf(passwordPF.getPassword());
                user = getAuthenticatedUser(name, password);
                //if the users details weren't in the database user will remain null and the else statement will catch it and notify the user sending them back to the login screen
                if (user != null) {
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(Login.this,
                            "Username or Password Invalid",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        //makes the cancel button close the login screen and reopen the dashboard
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login.this.dispose();
                Dashboard dashboardForm = new Dashboard();
            }
        });

        setVisible(true);
    }

    private final String DatabaseLocation = System.getProperty("user.dir") + "\\CS Project DB.accdb";
    private User getAuthenticatedUser(String name, String password) {
        User user = null;

        try{
            //Connects to database
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");

            //Creates the statement that will find if the entered Username and Password match with the database
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM Users WHERE Username=? AND password=?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            //saves the current users details once they have logged in
            if (resultSet.next()) {
                user = new User();
                user.setName(resultSet.getString("Username"));
                user.setEmail(resultSet.getString("Email"));
                user.setPassword(resultSet.getString("Password"));
            }

            stmt.close();
            con.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }
}