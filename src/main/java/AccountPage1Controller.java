import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class AccountPage1Controller {

    @FXML
    TextField balanceTextfield;

    @FXML
    public void initialize(){
        FileConnector fileConnector=new FileConnector();
        balanceTextfield.setEditable(false);
        fileConnector.readBalanceFile();
        balanceTextfield.setText(fileConnector.getLine());
    }

    @FXML
    public void handleNextPageToIncomePageButtonOnClick(ActionEvent event){


        Button next = (Button) event.getSource();


        Stage stage = (Stage) next.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Income.fxml"));
        try {
            stage.setScene(new Scene(loader.load(), 935, 597));
            stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
    @FXML
    public void handleNextPageToExpensePageButtonOnClick(ActionEvent event){
        Button next = (Button) event.getSource();

        Stage stage = (Stage) next.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Expense.fxml"));
        try {
            stage.setScene(new Scene(loader.load(), 935, 597));

            stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

}
