package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;


public class Login extends JDialog {
    //GUI code for the username, password and buttons
    private JPasswordField passwordPF;
    private JTextField usernameTF;
    private JButton loginBtn;
    private JButton cancelBtn;
    private JPanel loginPanel;
    private User user;

    //Creates the login window and sets its size and window title.
    public Login(JFrame parent) {
        super(parent);
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //Add an ActionListener to detect when the login button is pressed then saves the entered details as variables
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = usernameTF.getText();
                String password = String.valueOf(passwordPF.getPassword());
                user = getAuthenticatedUser(name, password);
                //if the users details weren't in the database user will remain null and the else statement will catch it
                //and send an error message while sending them back to the login window
                if (user != null) {
                    Login.this.dispose();
                    AddProduct addProductForm = new AddProduct();
                    addProductForm.setVisible(true);
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

    //An immutable variable that holds the file location of the database
    private final String DatabaseLocation = System.getProperty("user.dir") + "\\CS Project DB.accdb";

    //Creates a User object for authenticating that the user is in the database
    private User getAuthenticatedUser(String name, String password) {
        User user = null;
        try{
            //Connects to database using ucanaccess
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");

            //The SQL command used to check if the Username and password matches
            String sql = "SELECT * FROM Users WHERE Username=? AND password=?";
            //Prepares the statement that will compare the entered details to the database
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);

            //Executes the Statement to check if the entered information matches the database
            ResultSet resultSet = preparedStatement.executeQuery();

            //saves the current users details once they have logged in
            if (resultSet.next()) {
                user = new User();
                user.setName(resultSet.getString("Username"));
                user.setEmail(resultSet.getString("Email"));
                user.setPassword(resultSet.getString("Password"));
            }
            //Closes the connection
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }
}