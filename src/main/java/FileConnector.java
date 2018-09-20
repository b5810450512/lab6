import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class FileConnector {
//    private int sum;
    private String line = new String(); //ค่าbalance




    public String readBalanceFile() {
        try {
            Path file = Paths.get("C:\\Users\\USER\\Desktop\\labse06\\balance.txt");
            BufferedReader reader = Files.newBufferedReader(file,
                    StandardCharsets.UTF_8);

            line = reader.readLine();

//            while ((line = reader.readLine()) != null) {
//                //sum+=Integer.parseInt(line);
//                line=line;
//
//            }

//            System.out.println(sum);

            reader.close();
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
        return line;
    }

    public ArrayList<Income> readIncomeFile () {
        ArrayList<Income> incomes=new ArrayList<>();
        Income newIncome;
        try {
            Path file = Paths.get("C:\\Users\\USER\\Desktop\\labse06\\income.txt");
            BufferedReader reader = Files.newBufferedReader(file,StandardCharsets.UTF_8);

                while ((line = reader.readLine()) != null) {
                        String[] strings = line.split(" ");
                        newIncome = new Income(strings[0], strings[1], strings[2], strings[3], strings[4], Double.parseDouble(strings[5]));
                        incomes.add(newIncome);
                }
                reader.close();

        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
        return incomes;
    }

    public ArrayList<Expense> readExpenseFile(){
        ArrayList<Expense> expenses=new ArrayList<>();
        Expense newExpense;

        try {
            Path file = Paths.get("C:\\Users\\USER\\Desktop\\labse06\\expense.txt");
            BufferedReader reader = Files.newBufferedReader(file,StandardCharsets.UTF_8);

            //for(line = reader.readLine(); line != null; line= reader.readLine())
            //while ((line = reader.readLine()) != null)


            for(line = reader.readLine(); line != null; line= reader.readLine()) {
                String[] strings=line.split(" ");
                newExpense=new Expense(strings[0],strings[1],strings[2],strings[3],strings[4],Double.parseDouble(strings[5]));
                expenses.add(newExpense);

            }
            reader.close();
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
        return expenses;
    }



    public void writeBalanceFile(String balance) {

        try {
            File file = new File("C:\\Users\\USER\\Desktop\\labse06\\balance.txt");
            //  FileWriter writer = new FileWriter(file,false); // true จะเขียนต่อท้ายไฟล์
            FileWriter writer = new FileWriter(file, false); // true จะเขียนต่อท้ายไฟล์

//            for (int i = 21; i < 30; i++) {
//                writer.write("This is content line " + (i + 1)+System.getProperty( "line.separator" ));
//            }
           // writer.write(String.valueOf(" ") + System.getProperty("line.separator"));//เขียนละขึ้นบรรทัดใหม่
            writer.write(balance);

            // writer.write(String.valueOf(number));//เขียนต่อบรรทัดเดียวกัน

            writer.close();
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

        public void writeIncomeFile(Income income) {
            try {
                File file = new File("C:\\Users\\USER\\Desktop\\labse06\\income.txt");
                //  FileWriter writer = new FileWriter(file,false); // true จะเขียนต่อท้ายไฟล์
                FileWriter writer = new FileWriter(file, true); // true จะเขียนต่อท้ายไฟล์
               // writer.write(String.valueOf(" ") + System.getProperty("line.separator"));//เขียนละขึ้นบรรทัดใหม่
                writer.write(income.getDayColumn()+" "+income.getMonthColumn()+" "+income.getYearColumn()
                        +" "+income.getCategoryColumn()+" "+income.getDetailColumn()+" "+income.getAmountColumn()+System.getProperty("line.separator"));//เขียนละขึ้นบรรทัดใหม่


                writer.close();
            } catch (IOException e) {
                System.err.println("IOException: " + e.getMessage());
            }
        }

        public void writeExpenseFile(Expense expense) {
            try {
                File file = new File("C:\\Users\\USER\\Desktop\\labse06\\expense.txt");
                //  FileWriter writer = new FileWriter(file,false); // true จะเขียนต่อท้ายไฟล์
                FileWriter writer = new FileWriter(file, true); // true จะเขียนต่อท้ายไฟล์


                writer.write(expense.getDayColumn()+" "+expense.getMonthColumn()+" "+expense.getYearColumn()
                        +" "+expense.getCategoryColumn()+" "+expense.getDetailColumn()+" "+expense.getAmountColumn()+System.getProperty("line.separator"));//เขียนละขึ้นบรรทัดใหม่

                writer.close();
            } catch (IOException e) {
                System.err.println("IOException: " + e.getMessage());
            }
    }




    public String getLine() {
        return line;
    }


}

