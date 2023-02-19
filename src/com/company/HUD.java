package com.company;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class HUD {
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

    private final String DatabaseLocation = System.getProperty("user.dir") + "\\CS Project DB.accdb";

    private void Labels1(){
        try {
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM Goods WHERE ID = 1";
        }catch (Exception e){
            System.out.println(e);
        }

    }

}
