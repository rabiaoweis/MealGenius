
package team1.mealgenius;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

/**
 *
 * @author Sharon
 */
enum Meal {
        NONE, BREAKFAST, LUNCH, DINNER, SNACK
    };

public class DatabaseQuery {
    //Insert methods (only way to test database)
    public static void insertRecipe(String name, String directions, 
            Ingredient[] ingredients,String tags){
       
        String sql_RecipeInsert = "INSERT INTO Recipe(recipe_name, recipe_directions ,recipe_tags)"
                + "VALUES(?,?,?)";

        String sql_IngredientInsert = "INSERT INTO Ingredient(recipe_id, ingredient_name, ingredient_amount, ingredient_unit)"
                + "VALUES(?,?,?,?)";    

        try(var conn = DriverManager.getConnection(DatabaseConnection.URL);
            var pstmtRecipe = conn.prepareStatement(sql_RecipeInsert);
            var pstmtIngredient = conn.prepareStatement(sql_IngredientInsert)){

            //add initial values
            pstmtRecipe.setString(1,name); 
            pstmtRecipe.setString(2,directions);
            pstmtRecipe.setString(3,tags);
            pstmtRecipe.executeUpdate();

            //get recipe id for next step to put in ingredient table
            ResultSet rs = pstmtRecipe.getGeneratedKeys();
            rs.next();
            int recipe_id = rs.getInt(1);

            //add ingredients to table
            for (Ingredient ingredient : ingredients){
                pstmtIngredient.setInt(1,recipe_id);//recipe_id same
                pstmtIngredient.setString(2, ingredient.getName());
                pstmtIngredient.setDouble(3, ingredient.getAmount());
                pstmtIngredient.setString(4, ingredient.getUnit());
                pstmtIngredient.executeUpdate();
            }
            System.out.println("Recipe Added to database");           
        } catch(SQLException e){
            System.out.println(e.getMessage());

        }
    }  

    public static String[] selectRecipeList(){//returns Recipe Names only    
        String recipe_Select = "SELECT recipe_name FROM Recipe;";
        String[] array= new String[0];
        try(var conn = DriverManager.getConnection(DatabaseConnection.URL);
            var stmt = conn.createStatement()){
            //get the results and find rows to make array
            ResultSet rs = stmt.executeQuery(recipe_Select);

            if(rs != null){
                String list = "";
                while (rs.next()){
                    list = list + rs.getString("recipe_name")+"\t";
                }
                array = list.split("\t");
                return array;

            }   
            return array; 
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return array;
    }
    
    public static String selectCalendarMeal(LocalDate dt, int meal_id)
    {
        String colname = String.format("calendar_meal_%d", meal_id);
        String recipe_name_q = String.format(
                "select recipe_name from recipe where recipe_id = (select %s from Calendar where date='%s');",
                colname,
                dt.toString());
        System.out.println(recipe_name_q);

        try(var conn = DriverManager.getConnection(DatabaseConnection.URL);
            var stmt = conn.createStatement())
        {
            ResultSet rs = stmt.executeQuery(recipe_name_q);

            if(rs != null){
                return rs.getString("recipe_name");
            }            
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return "";
    }


    //main method for testing
    public static void main(String[] args) {
//        String[] recipes = selectRecipeList();
//        for (String recipe : recipes) {
//            System.out.println(recipe);
//        }
        LocalDate dt = LocalDate.parse("2025-05-02");
        System.out.println(selectCalendarMeal(dt,1));
        System.out.println("awais");
    }
}
