package team1.mealgenius;
//importing packages to help with dates
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
/**
 *
 * @author Sharon Mathys
 */
public class MealCalendar {
    
    
    //private Date date; // date should be YYYY-MM-DD initially
    private LocalDate date = LocalDate.now();
    private DayOfWeek dayOfWeek;
    private String breakfast;
    private int breakfast_id;
    private String lunch;
    private int lunch_id;
    private String dinner;
    private int dinner_id;
    private String snack;
    private int snack_id;
   // public MealCalendar(Date date, Day dayofweek, String breakfast, String lunch,
           // String dinner, String snack){
  //constructor here
    public MealCalendar(int year, int month, int day){
    this.date.of(year,month,day);
    this.dayOfWeek = this.date.getDayOfWeek();
    }
   // overloaded for testing
    public MealCalendar(int year, int month, int day, String breakfast, String lunch,
            String dinner, String snack){
        this.date.of(year,month,day);
        this.dayOfWeek = this.date.getDayOfWeek();
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.snack= snack;
    }
   // }
   // public String getDate(){
        //return date as string for Display/Rabia
   // }
  //  public String getDateSQL(){
    // return date for SQL
    
    // public setBreakfast (overload for testing)
    public void setBreakfast(String breakfast){ this.breakfast = breakfast;}
    
    public void setBreakfast(String breakfast, int breakfast_id){
        this.breakfast = breakfast;
        this.breakfast_id = breakfast_id;
    }
    //setLunch, both for testing and overload
    public void setLunch(String lunch){this.lunch = lunch;}
    
    public void setLunch(String lunch, int Lunch_id){
        this.lunch = lunch;
        this.lunch_id = lunch_id;}
    //setDinner, testing and overload
    public void setDinner(String dinner){this.dinner =dinner;}
    
    public void setDinner(String dinner, int dinner_id){
        this.dinner= dinner;
        this.dinner_id = dinner_id;}
    
    //setSnack, testing and overload
    public void setSnack(String snack){this.snack = snack;}
    
    public void setSnack(String snack, int snack_id){
        this.snack = snack;
        this.snack_id = snack_id;}
    
    public void setDate(int year, int month, int day){//autosets day of week
        this.date.of(year,month,day);
        this.dayOfWeek = this.date.getDayOfWeek();
    }
    // all gets need some sort of try/except in reality.
    public String getBreakfastName(){return breakfast;}
    
    public int getBreakfastId(){return breakfast_id;}
    
    public String getLunchName(){return lunch;}
    
    public int getLunchId() {return lunch_id;}
    
    public String getDinnerName(){return dinner;}
    
    public int getDinnerId(){return dinner_id;}
    
    public String getDayOfWeek(){return dayOfWeek.name();}
    
    public String getDateISO(){// formatting date as YYYY-MM-DD for SQL
        return date.toString();}
    
    public String getDateDisplayShort(){// formatting date as MM/DD/YY
       return date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
       
    }
  
}