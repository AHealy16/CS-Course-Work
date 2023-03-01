package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HUD extends JDialog{
    private JPanel HUDForm;
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel amountLabel;
    private JLabel perishLabel;
    private JLabel id1;
    private JLabel id2;
    private JLabel id3;
    private JLabel name1;
    private JLabel name2;
    private JLabel name3;
    private JLabel amount1;
    private JLabel amount2;
    private JLabel amount3;
    private JLabel perish1;
    private JLabel perish2;
    private JLabel perish3;
    private JButton loadButton;
    private JButton add_product_page;

    private final String DatabaseLocation = System.getProperty("user.dir") + "\\CS Project DB.accdb";

    public HUD(JFrame parent) {
        super(parent);
        setTitle("Create a new account");
        setContentPane(HUDForm);
        setMinimumSize(new Dimension(480, 700));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        add_product_page.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HUD.this.dispose();
                AddProduct addproductForm = new AddProduct(parent);
                addproductForm.setVisible(true);
            }
        });

        //Loads the information from the database
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Labels1();
                Labels2();
                Labels3();
            }
        });

        setVisible(true);
    }


    private void Labels1(){
        int ID1;
        String NAME1;
        int AMOUNT1;
        boolean PERISH1;

        try {
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM Goods WHERE ID = 1";
            ResultSet rs = Database_Management.executeQuery(con, sql);
            ID1 = rs.getInt("ID");
            NAME1 = rs.getString("Goods_Name");
            AMOUNT1 = rs.getInt("Amount");
            PERISH1 = rs.getBoolean("Perishable");

            con.close();
            stmt.close();
        }catch (Exception e){
            System.out.println(e);
        }

    }

    private void Labels2(){
        int ID2;
        String NAME2;
        int AMOUNT2;
        boolean PERISH2;

        try {
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM Goods WHERE ID = 2";
            ResultSet rs = Database_Management.executeQuery(con, sql);
            ID2 = rs.getInt("ID");
            NAME2 = rs.getString("Goods_Name");
            AMOUNT2 = rs.getInt("Amount");
            PERISH2 = rs.getBoolean("Perishable");

            id2.setText(String.valueOf(ID2));
            name2.setText(NAME2);
            amount2.setText(String.valueOf(AMOUNT2));
            perish2.setText(String.valueOf(PERISH2));

            con.close();
            stmt.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private void Labels3(){
        int ID3;
        String NAME3;
        int AMOUNT3;
        boolean PERISH3;

        try {
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM Goods WHERE ID = 3";
            ResultSet rs = Database_Management.executeQuery(con, sql);
            ID3 = rs.getInt("ID");
            NAME3 = rs.getString("Goods_Name");
            AMOUNT3 = rs.getInt("Amount");
            PERISH3 = rs.getBoolean("Perishable");

            con.close();
            stmt.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
