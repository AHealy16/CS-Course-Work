package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CSProjectGUI extends JDialog {
    //GUI for registration system formatted into code, it takes the users Name, Email and Password along with confirming
    //the Users password
    private JTextField nameTF;
    private JTextField emailTF;
    private JPasswordField passwordPF;
    private JPasswordField confirmPasswordPF;
    private JButton registerBtn;
    private JButton cancelBtn;
    private JPanel registerPanel;
    private User user;

    //the code to run the actual registration GUI code, it defines the dimensions of the window size along with the window
    //title and uses inheritance.
    public CSProjectGUI(JFrame parent) {
        super(parent);
        setTitle("Create a new account");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(480, 700));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //makes the register button work by using an ActionListener which find out when the button is clicked
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }


        });

        //makes the cancel button close the register tab and reopen the dashboard tab by using an ActionListener
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CSProjectGUI.this.dispose();
                Dashboard dashboardForm = new Dashboard();

            }
        });

        setVisible(true);
    }

    //This part of the code takes the information from the GUI and stores it all as individual variables
    private void registerUser() {
        String name = nameTF.getText();
        String email = emailTF.getText();
        String password = String.valueOf(passwordPF.getPassword());
        String confirmPassword = String.valueOf(confirmPasswordPF.getPassword());
        String userNameCheck;

        try{
            //Checks that the name being entered isn't one that is already in the Database to prevent copies and allowing
            //for a unique primary key for the database
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM Users WHERE Username ='" + name;
            ResultSet rs = Database_Management.executeQuery(con, sql);
                userNameCheck = rs.getString("Username");

                //Error message for if the username inputted is already used
                if (name != userNameCheck){
                    JOptionPane.showMessageDialog(this,
                            "This Username is taken",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                con.close();
                stmt.close();

        }catch(Exception e){
            System.out.println(e);
        }

        //checks that they haven't left any of the text fields blank by seeing if any of the set variables are empty
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Checks to see if password doesn't equal confirmpassword by comparing the variables and displays an error message
        //if they don't match
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this,
                    "Passwords do not match",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        //This part of the code sends the users information to the database and then disposes of the register window
        user = addUserToDatabase(name, email, password);
        if (user != null) {
            dispose();
        } else {
            //Essentially just exception handles and sends an error message to the user if data doesn't go to database
            JOptionPane.showMessageDialog(this,
                    "Failed to register new user",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    //An immutable variable that holds the file location of the database
    private final String DatabaseLocation = System.getProperty("user.dir") + "\\CS Project DB.accdb";

    private User addUserToDatabase(String name, String email, String password) {
        User user = null;

        //Uses a connection statement and ucanaccess software to establish a connection to the database
        //no username or password is required since the database doesn't use either
        try {
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");

            //Creates a String variable that serves as the actual SQL command and defines the table the data will be entered
            //into (Users) and the columns the data will go into (Username, Password, Email) and which values go where (VALUES (?, ?, ?)
            String sql = "INSERT INTO Users (Username, Password, Email)" + "VALUES (?, ?, ?)";
            //Combines the data from the user and the actual SQL
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            //Closes the registration window
            CSProjectGUI.this.dispose();
            //Opens the Dashboard window
            Dashboard dashboardForm = new Dashboard();

            //Executes the preparedStatement from above and sends the data to the database while also checking that it was actually sent.
            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0){
                user = new User();
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);
            }
            //closes connection to the database
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getUser() {
        return this.user;
    }
}