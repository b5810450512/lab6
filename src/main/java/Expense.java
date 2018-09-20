import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Expense {
    private  SimpleStringProperty dayColumn;
    private SimpleStringProperty monthColumn;
    private  SimpleStringProperty yearColumn;
    private  SimpleStringProperty categoryColumn;
    private  SimpleStringProperty detailColumn;
    private  SimpleDoubleProperty amountColumn;
    private double expenseMoney;

    public Expense() {
    }

    public Expense(String newDay, String newMonth, String newYear, String newCategory, String newDetail, Double newAmount) {
        this.expenseMoney+=newAmount;
        this.dayColumn = new SimpleStringProperty(newDay);
        this.monthColumn = new SimpleStringProperty(newMonth);
        this.yearColumn = new SimpleStringProperty(newYear);
        this.categoryColumn = new SimpleStringProperty(newCategory);
        this.detailColumn = new SimpleStringProperty(newDetail);
        this.amountColumn = new SimpleDoubleProperty(newAmount);
    }

    public String getDayColumn() {
        return dayColumn.get();
    }

    public void setDayColumn(String dayColumn) {
        this.dayColumn.set(dayColumn);
    }

    public String getMonthColumn() {
        return monthColumn.get();
    }


    public void setMonthColumn(String monthColumn) {
        this.monthColumn.set(monthColumn);
    }

    public String getYearColumn() {
        return yearColumn.get();
    }

    public void setYearColumn(String yearColumn) {
        this.yearColumn.set(yearColumn);
    }

    public String getCategoryColumn() {
        return categoryColumn.get();
    }


    public void setCategoryColumn(String categoryColumn) {
        this.categoryColumn.set(categoryColumn);
    }

    public String getDetailColumn() {
        return detailColumn.get();
    }

    public void setDetailColumn(String detailColumn) {
        this.detailColumn.set(detailColumn);
    }

    public double getAmountColumn() {
        return amountColumn.get();
    }

    public void setAmountColumn(double amountColumn) {
        this.amountColumn.set(amountColumn);
    }

    public double getExpenseMoney() {
        return expenseMoney;
    }
}
