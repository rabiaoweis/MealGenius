/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package team1.mealgenius;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class MealPlannerTable extends JFrame {
    private static final String[] DAYS = {
        "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    };
    private static final String[] MEALS = {
        "Breakfast", "Lunch", "Dinner", "Snack"
    };
    private static final String DB_URL = "jdbc:sqlite:meals.db";

    public MealPlannerTable() {
        setTitle("Meal Planner");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 400);

        // Add "Meal Type" as the first column header
        String[] columnHeaders = new String[DAYS.length + 1];
        columnHeaders[0] = "Meal Type";
        System.arraycopy(DAYS, 0, columnHeaders, 1, DAYS.length);

        DefaultTableModel model = new DefaultTableModel(columnHeaders, 0) {
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };

        // Add rows: each row starts with a meal type
        for (String meal : MEALS) {
            Object[] row = new Object[DAYS.length + 1];
            row[0] = meal;
            model.addRow(row);
        }

        JTable table = new JTable(model);
        table.setRowHeight(30);
        
        String[] mealOptions = DatabaseQuery.selectRecipeList();

        // Fetch combo box values from SQLite
        //Vector<String> mealOptions = fetchMealOptionsFromDB();

        // Add combo box editors to all editable cells
        JComboBox<String> comboBox = new JComboBox<>(mealOptions);
        for (int i = 1; i <= DAYS.length; i++) {
            TableColumn col = table.getColumnModel().getColumn(i);
            col.setCellEditor(new DefaultCellEditor(new JComboBox<>(mealOptions)));
        }

        add(new JScrollPane(table));
        setVisible(true);
    }

//    private Vector<String> fetchMealOptionsFromDB() {
//        Vector<String> options = new Vector<>();
//        try (Connection conn = DriverManager.getConnection(DB_URL);
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery("SELECT meal_name FROM meals")) {
//            while (rs.next()) {
//                options.add(rs.getString("meal_name"));
//            }
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(this, "Error loading meal data: " + e.getMessage());
//        }
//        return options;
//    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MealPlannerTable::new);
    }
}
