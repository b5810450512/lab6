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


public class ExpenseViewController {


    @FXML
    TableView<Expense> expenseTable;
    @FXML
    TableColumn<Expense,String> dayColumn;
    @FXML
    TableColumn<Expense,String> monthColumn;
    @FXML
    TableColumn<Expense,String> yearColumn;
    @FXML
    TableColumn<Expense,String> categoryColumn;
    @FXML
    TableColumn<Expense,String> detailColumn;
    @FXML
    TableColumn<Expense,Double> amountColumn;
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
    private ObservableList<String> categoryList=FXCollections.observableArrayList("เดินทาง","อาหาร");

    @FXML public void initialize(){
        //FileConnector fileConnector=new FileConnector();

        //fileConnector.writeExpenseFile(new Expense("1","มค","2561","อาหาร","กินเคเอฟซี",150.0));


        dayCombobox.setValue("เลือกวันที่");
        dayCombobox.setItems(dayList);
        monthCombobox.setValue("เลือกเดือน");
        monthCombobox.setItems(monthList);
        categoryCombobox.setValue("เลือกประเภท");
        categoryCombobox.setItems(categoryList);
        dayColumn.setCellValueFactory(new PropertyValueFactory<Expense,String>("dayColumn"));
        monthColumn.setCellValueFactory(new PropertyValueFactory<Expense,String>("monthColumn"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Expense,String>("yearColumn"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Expense,String>("categoryColumn"));
        detailColumn.setCellValueFactory(new PropertyValueFactory<Expense,String>("detailColumn"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<Expense,Double>("amountColumn"));

        expenseTable.setItems(getData());

    }
    public ObservableList<Expense> getData(){
        FileConnector fileConnector=new FileConnector();
        ArrayList<Expense> allExpense=fileConnector.readExpenseFile();
        ObservableList<Expense> data=FXCollections.observableArrayList();
        for(Expense expense:allExpense){
            data.add(new Expense(expense.getDayColumn(),expense.getMonthColumn(), expense.getYearColumn(), expense.getCategoryColumn()
            , expense.getDetailColumn(),expense.getAmountColumn()));
        }
        return data;

    }


    @FXML
    public  void handleAddExpenseButtonOnClick(ActionEvent event){
        FileConnector fileConnector=new FileConnector();
        ObservableList<Expense> data=FXCollections.observableArrayList();
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
                Expense expense = new Expense(dayCombobox.getValue().toString(), monthCombobox.getValue().toString(), yearTextfield.getText(), categoryCombobox.getValue().toString(), detailTextfield.getText(), Double.parseDouble(amountTextfield.getText()));

                fileConnector.readBalanceFile();
                if (Double.parseDouble(fileConnector.getLine()) > expense.getExpenseMoney()) {
                    fileConnector.writeBalanceFile(String.valueOf(Double.parseDouble(fileConnector.getLine()) - expense.getExpenseMoney()));
                } else {
                    fileConnector.writeBalanceFile("0.0");
                }

                fileConnector.writeExpenseFile(expense);

                dayColumn.setCellValueFactory(new PropertyValueFactory<Expense, String>("dayColumn"));
                monthColumn.setCellValueFactory(new PropertyValueFactory<Expense, String>("monthColumn"));
                yearColumn.setCellValueFactory(new PropertyValueFactory<Expense, String>("yearColumn"));
                categoryColumn.setCellValueFactory(new PropertyValueFactory<Expense, String>("categoryColumn"));
                detailColumn.setCellValueFactory(new PropertyValueFactory<Expense, String>("detailColumn"));
                amountColumn.setCellValueFactory(new PropertyValueFactory<Expense, Double>("amountColumn"));

                expenseTable.setItems(getData());
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
