package com.example.mainhud;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    Button Confirm_Button;
    TextField name_Product;
    TextField amount_Good;
    TextField type_Good;
    CheckBox perishable;
    @FXML



    public void button_Press(){
        EventHandler<ActionEvent> confirm_Product = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                add_product();
            }
        };
        Confirm_Button.setOnAction(confirm_Product);
    }


    protected void add_product() {
        String name = name_Product.getText();
        String type = type_Good.getText();
        int amount = amount_Good.anchorProperty().getValue();
        boolean perish;
        if (perishable.isSelected()){
            perish = true;
        }

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        if (name.trim().equals("") || amount == 0 || type.trim().equals("")) {
                errorAlert.setHeaderText("Input not valid");
                errorAlert.setContentText("A field has not been inputted");
                errorAlert.showAndWait();
        }



    }
}


