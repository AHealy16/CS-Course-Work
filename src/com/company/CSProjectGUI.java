package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

//All registration GUI code
public class CSProjectGUI extends JDialog {
    //GUI for registration system formatted into code
    private JTextField nameTF;
    private JTextField emailTF;
    private JPasswordField passwordPF;
    private JPasswordField confirmPasswordPF;
    private JButton registerBtn;
    private JButton cancelBtn;
    private JPanel registerPanel;
    private User user;

    //the code to run the actual registration GUI code
    public CSProjectGUI(JFrame parent) {
        super(parent);
        setTitle("Create a new account");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(480, 700));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //makes the register button work
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }


        });

        //makes the cancel button close the register tab and reopen the dashboard tab
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CSProjectGUI.this.dispose();
                Dashboard dashboardForm = new Dashboard();

            }
        });

        setVisible(true);
    }

    //The code to confirms that everything that has been entered by the user is correct
    private void registerUser() {
        String name = nameTF.getText();
        String email = emailTF.getText();
        String password = String.valueOf(passwordPF.getPassword());
        String confirmPassword = String.valueOf(confirmPasswordPF.getPassword());
        String userNameCheck;

        //Uses the User object to set all the details about the user making these details obtainable to other classes
        User user1 = new User();
        user1.setEmail(email);
        user1.setName(name);
        user1.setPassword(password);

        try{
            //checks that the name username being entered is unique
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM Users WHERE Username ='" + name;
            ResultSet rs = Database_Management.executeQuery(con, sql);
                userNameCheck = rs.getString("Username");

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

        //Checks that all the fields were entered
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Checks the passwords entered match
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this,
                    "Passwords do not match",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        user = addUserToDatabase(name, email, password);
        if (user != null) {
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Failed to register new user",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    //Database connection location
    private final String DatabaseLocation = System.getProperty("user.dir") + "\\CS Project DB.accdb";


    private User addUserToDatabase(String name, String email, String password) {
        User user = null;

        User user2 = new User();
        password = user2.getPassword();
        //Connection to database
        try {
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");

            //prepares the statement that will insert the users details into the database
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO Users (Username, Password, Email)" + "VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            CSProjectGUI.this.dispose();
            Dashboard dashboardForm = new Dashboard();

            //executes the statement
            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0){
                user = new User();
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);

            }

            //closes connection to the database
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}