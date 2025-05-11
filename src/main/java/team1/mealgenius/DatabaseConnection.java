package team1.mealgenius;

import java.sql.DriverManager;
import java.sql.SQLException;
//import java.sql.Connection; // use this instead of drivermanager.yy

/**
 *
 * @author Sharon
 */
public class DatabaseConnection {
    public static final String URL = "jdbc:sqlite:recipe.db";
    
    //create the database
    public static void create(){
        //create the database. Note: code follows tutorial from sqlite
        // var is used to dynamically type. Unsure what a conn type is so leavi
        try (var conn = DriverManager.getConnection(URL)){
            if(conn != null){
                var meta = conn.getMetaData();
                System.out.println("The driver name is "+ meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
    // connect to the database
    public static void connect(){
       //see if we can just use this string url
    try(var conn = DriverManager.getConnection(URL)){
        System.out.println("Connection to SQLite has been established");
    } catch(SQLException e){
        System.out.println(e.getMessage());    }
    }       
    
    // create the tables
    public static void createTables(){
        // connection string is still public static and in scope
        // have SQL to create table
        // must always be Recipe table first
        /**var sql_recipe = "CREATE TABLE IF NOT EXISTS Recipe("
                + "  recipe_id INTEGER PRIMARY KEY,"
                + "  recipe_name TEXT UNIQUE NOT NULL,"
                + "  recipe_directions TEXT,"
                + "  recipe_tags TEXT);";*/
        
        var sql_calendar = "CREATE TABLE IF NOT EXISTS Calendar("
                + "  date TEXT PRIMARY KEY,"
                + "  day_of_week TEXT,"
                + "  calendar_breakfast TEXT,"
                + "  calendar_breakfast_id INTEGER,"
                + "  calendar_lunch TEXT,"
                + "  calendar_lunch_id INTEGER,"
                + "  calendar_dinner TEXT,"
                + "  calendar_dinner_id INTEGER"
                + "  calendar_snack TEXT,"
                + "  calendar_snack_id INTEGER);";
                
        /**var sql_ingredient = "CREATE TABLE IF NOT EXISTS Ingredient("
                + "  recipe_id INTEGER,"
                + "  ingredient_name TEXT,"
                + "  ingredient_amount FLOAT,"
                + "  ingredient_unit TEXT,"  
                + " PRIMARY KEY(ingredient_name, recipe_id),"
                + " FOREIGN KEY (recipe_id) REFERENCES Recipe(recipe_id));";**/   
                
        try(var conn = DriverManager.getConnection(URL);
                var stmt = conn.createStatement()){
            //now the creation
            //stmt.executeUpdate(sql_recipe);
            stmt.executeUpdate(sql_calendar);
            //stmt.executeUpdate(sql_ingredient);
        } catch (SQLException e){
    System.out.println(e.getMessage());
        }     
    }      
           
    //drop database tables (only use for testing)
    public static void dropTables(){
        /**var sql_dropTables ="DROP TABLE IF EXISTS Ingredient;"
                + "DROP TABLE IF EXISTS Direction;"
                + "DROP TABLE IF EXISTS Tag;"
                + "DROP TABLE IF EXISTS Calendar;"
                + "DROP TABLE IF EXISTS Recipe;";      **/
        var sql_dropTables = "DROP TABLE IF EXIST Calendar;";
       
        try(var conn = DriverManager.getConnection(URL);
                var stmt = conn.createStatement()){
            //now the creation
            stmt.executeUpdate(sql_dropTables);}
        catch (SQLException e){
            System.out.println(e.getMessage());
            }     
        }      
    
            
    //main method
    public static void main(String[] args) {
       // dropTables();  
       // createTables();
    }
    
}
