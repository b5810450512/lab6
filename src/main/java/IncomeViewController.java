import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class IncomeViewController {
    @FXML
    TableView<Income> incomeTable;
    @FXML
    TableColumn<Income,String> dayColumn;
    @FXML
    TableColumn<Income,String> monthColumn;
    @FXML
    TableColumn<Income,String> yearColumn;
    @FXML
    TableColumn<Income,String> categoryColumn;
    @FXML
    TableColumn<Income,String> detailColumn;
    @FXML
    TableColumn<Income,Double> amountColumn;
    @FXML
    TextField yearTextfield;
    @FXML
    TextField detailTextfield;
    @FXML
    TextField amountTextfield;
    @FXML
    ComboBox dayCombobox;
    @FXML
    ComboBox monthCombobox;
    @FXML
    ComboBox categoryCombobox;
    @FXML Label warningNullLabel;
    @FXML Label dateWariningLabel;
    @FXML Label moneyLabel;



    private ObservableList<String> dayList=FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31");
    private ObservableList<String> monthList=FXCollections.observableArrayList("มค","กพ","มีค","เมย","พค","มิย","กค","สค","กย","ตค","พย","ธค");
    private ObservableList<String> categoryList=FXCollections.observableArrayList("เงินเดือน","เก็บค่าเช่า");

    @FXML public void initialize(){
        //FileConnector fileConnector=new FileConnector();
        //fileConnector.writeIncomeFile(new Income("1","มค","2561","เงินเดือน","เดือนแรก",100.0));

        dayCombobox.setValue("เลือกวันที่");
        dayCombobox.setItems(dayList);
        monthCombobox.setValue("เลือกเดือน");
        monthCombobox.setItems(monthList);
        categoryCombobox.setValue("เลือกประเภท");
        categoryCombobox.setItems(categoryList);
        dayColumn.setCellValueFactory(new PropertyValueFactory<Income,String>("dayColumn"));
        monthColumn.setCellValueFactory(new PropertyValueFactory<Income,String>("monthColumn"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Income,String>("yearColumn"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Income,String>("categoryColumn"));
        detailColumn.setCellValueFactory(new PropertyValueFactory<Income,String>("detailColumn"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<Income,Double>("amountColumn"));


        incomeTable.setItems(getData());
    }
    public ObservableList<Income> getData(){
        FileConnector fileConnector=new FileConnector();
        ArrayList<Income> allIncome=fileConnector.readIncomeFile();
        ObservableList<Income> data=FXCollections.observableArrayList();

        for(Income income:allIncome){
            data.add(new Income(income.getDayColumn(),income.getMonthColumn(), income.getYearColumn(), income.getCategoryColumn()
                    , income.getDetailColumn(),income.getAmountColumn()));
        }
        return data;

    }

    @FXML
    public  void handleAddIncomeButtonOnClick(ActionEvent event){
        FileConnector fileConnector=new FileConnector();
        ObservableList<Income> data=FXCollections.observableArrayList();
        if(dayCombobox.getValue().toString()!="เลือกวันที่"&&!monthCombobox.getValue().toString().equals("เลือกเดือน")&&!yearTextfield.getText().equals("")&&!categoryCombobox.getValue().toString().equals("เลือกประเภท")&&!detailTextfield.getText().equals("")&&!amountTextfield.getText().equals("")){
            if((monthCombobox.getValue().toString().equals("เมย")||monthCombobox.getValue().toString().equals("มิย")
                    ||monthCombobox.getValue().toString().equals("กย")
                    ||monthCombobox.getValue().toString().equals("พย"))&&dayCombobox.getValue().toString().equals("31")){
                warningNullLabel.setText("");
                dateWariningLabel.setText("วันและเดือนไม่สอดคล้อง");
                moneyLabel.setText("");


            }else if(monthCombobox.getValue().toString().equals("กพ")&&(dayCombobox.getValue().toString().equals("31")||dayCombobox.getValue().toString().equals("30"))){
                warningNullLabel.setText("");
                dateWariningLabel.setText("วันและเดือนไม่สอดคล้อง");
                moneyLabel.setText("");
            }
            else if(Double.parseDouble(amountTextfield.getText())<0){
                moneyLabel.setText("โปรดกรอกเลขจำนวนบวก");
                warningNullLabel.setText("");
                dateWariningLabel.setText("");


            }
            else {
                Income income = new Income(dayCombobox.getValue().toString(), monthCombobox.getValue().toString(), yearTextfield.getText(), categoryCombobox.getValue().toString(), detailTextfield.getText(), Double.parseDouble(amountTextfield.getText()));

                fileConnector.readBalanceFile();

                fileConnector.writeBalanceFile(String.valueOf(Double.parseDouble(fileConnector.getLine()) + income.getIncomeMoney()));


                fileConnector.writeIncomeFile(income);

                dayColumn.setCellValueFactory(new PropertyValueFactory<Income, String>("dayColumn"));
                monthColumn.setCellValueFactory(new PropertyValueFactory<Income, String>("monthColumn"));
                yearColumn.setCellValueFactory(new PropertyValueFactory<Income, String>("yearColumn"));
                categoryColumn.setCellValueFactory(new PropertyValueFactory<Income, String>("categoryColumn"));
                detailColumn.setCellValueFactory(new PropertyValueFactory<Income, String>("detailColumn"));
                amountColumn.setCellValueFactory(new PropertyValueFactory<Income, Double>("amountColumn"));

                incomeTable.setItems(getData());
                warningNullLabel.setText("");
                dateWariningLabel.setText("");
                moneyLabel.setText("");


                yearTextfield.clear();
                detailTextfield.clear();
                amountTextfield.clear();
                dayCombobox.setValue("เลือกวันที่");
                monthCombobox.setValue("เลือกเดือน");
                categoryCombobox.setValue("เลือกประเภท");
            }
        }
        else{
            warningNullLabel.setText("โปรดกรอกข้อมูลให้ครบถ้วน");
            dateWariningLabel.setText("");
            moneyLabel.setText("");


        }

    }
    @FXML
    public void handleBackButtonOnClick(ActionEvent event){
        Button next = (Button) event.getSource();

        Stage stage = (Stage) next.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Page1.fxml"));
        try {
            stage.setScene(new Scene(loader.load(), 600, 228));

            stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }










}
