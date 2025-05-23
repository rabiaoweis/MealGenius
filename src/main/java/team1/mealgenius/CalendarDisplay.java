package team1.mealgenius;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
//import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
//import javax.swing.table.TableColumnModel;
import java.awt.Dimension;
import java.time.LocalDate;
import static team1.mealgenius.DatabaseQuery.selectCalendarMeal;

/**
 *
 * @author Rabia
 */
public class CalendarDisplay { // same as App.java
    
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Calendar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LocalDate dt = LocalDate.parse("2025-05-02");

        String[] columns = {"","Sunday", "Monday", "Tuesday", "Wednesday","Thursday","Friday","Saturday"};
        String[][] data = {
            {"Breakfast", DatabaseQuery.selectCalendarMeal(dt,1) ,"Cereal", "French Toast", "Pancakes", "Power Smoothie","Omlette","Avocado Toast"},
            {"Lunch", "Pasta", "Fried Rice", "Fish Taco", "Pizza","Burrito","Burger","Chicken Sandwhich"},
            {"Dinner","Biryani", "Chicken Pot Pie", "BBQ chicken", "Daal","Mixed Veggi Stew", "Lasagna","Chicken Soup"},
            {"Snack", "Dried Mango", "Strawberry bowl", "Jello","Nuts","Hummus","Cheese stick","Protein Bar"} 
        }; // New Calendar sunday = make fake one:

        JTable calendar = new JTable(data, columns);

        calendar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < calendar.getColumnCount(); i++) {
            TableColumn column = calendar.getColumnModel().getColumn(i);
            column.setPreferredWidth(150); // Uniform width for all columns
        }
        calendar.setRowHeight(100);

        JScrollPane scrollPane = new JScrollPane(calendar);
        scrollPane.setPreferredSize(new Dimension(1220, 430));  // Width x Height
        frame.add(scrollPane);
        frame.pack();

        frame.setVisible(true);
    } 
    
    public static String[] mealweek(){
        String[] meals =  {"Dinner","Biryani", "Chicken Pot Pie", "BBQ chicken", "Daal","Mixed Veggi Stew", "Lasagna","Chicken Soup"};
        return meals;
    }
}
