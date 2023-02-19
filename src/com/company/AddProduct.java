package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class AddProduct extends JDialog {
    private JButton confirmButton;
    private JTextField productNameTF;
    private JTextField amountTF;
    private JTextField typeTF;
    private JCheckBox perishableCB;
    private JPanel addProductPanel;

    public AddProduct(JFrame parent) {
        super(parent);
        setTitle("Add Product");
        setContentPane(addProductPanel);
        setMinimumSize(new Dimension(480, 700));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add_Product();
            }
        });
    }

    //Database connection location
    private final String DatabaseLocation = System.getProperty("user.dir") + "\\CS Project DB.accdb";

    private void add_Product() {
        String name = productNameTF.getText();
        int amount = Integer.parseInt(amountTF.getText());
        String type = typeTF.getText();
        Boolean perish = false;
        if (perishableCB.isSelected()) {
            perish = true;
        }

        //checks that all fields are entered
        if (name.isEmpty() || amount == 0 || type.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");

            Statement stmt = con.createStatement();
            String sql = "INSERT INTO Goods (Goods_Name, Perishable, Amount, Type)" + "VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setBoolean(2, perish);
            preparedStatement.setInt(3, amount);
            preparedStatement.setString(4, type);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
