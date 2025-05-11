package team1.mealgenius;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Sharon
 */
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
    //Display Method


    //Select Methods

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
       
}   return array; 
    } catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return array;
    }

//    
//    public static String[] searchRecipeName(String search){// search by name
//    
//    
//    
//    public static Recipe getRecipe(String recipeName){
//        //gets a specific object, used for chosing from list
//    }
//    
//    }
//    
//    public static  filterRecipe(){}
    
    //Insert Methods
    
    
    
    //Update Methods
    
    
    //Delete Methods
//main method for testing
    public static void main(String[] args){
    
String[] recipes = selectRecipeList();
for (String recipe : recipes){
    System.out.println(recipe);
}
    }
    
}
/**
 * Ingredient[] oatmeal = {new Ingredient("instant oatmeal",3/4.0,"cup"),
        new Ingredient("freeze-dried raspberries",1/4.0,"cup"),
        new Ingredient("water",3/4,"cup"), new Ingredient("milk",1/8.0,"cup")};
    
    Ingredient[] cereal= {new Ingredient("cheerios", 3/4.0, "cup"),
        new Ingredient("freeze-dried strawberries", 1.0/4.0,"cup"),
        new Ingredient("milk",1/2.0,"cup")};
    
    Ingredient[] pbsandwich = {new Ingredient("bread",2,"slices"),
        new Ingredient("peanut butter",3,"TBSP"), new Ingredient("banana",1/2.0,"")};
    
    String oatmeal_directions = """
                                1. Mix the instant oatmeal and water together and microwave for 90 seconds.
                                2. Crush the raspberries and sprinkle on oatmeal. Top with milk and serve.""";
    
    String cereal_directions = """
                               1. Crush strawberries and add to cereal.
                               2. Add milk and enjoy.""";
    
    String pbb_directions = """
                            1. Apply peanut butter to both slices of bread.
                            2. Slice the banana and put on one slice and close.""";
    
    insertRecipe("Oatmeal with Raspberries and Milk",oatmeal_directions,oatmeal,"gluten-free, vegetarian");
    insertRecipe("Cheerios and Milk",cereal_directions,cereal,"gluten-free, vegetarian");
    insertRecipe("PB&B Sandwich",pbb_directions,pbsandwich,"vegetarian");
 */