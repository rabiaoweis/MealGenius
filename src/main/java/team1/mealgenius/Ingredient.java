package team1.mealgenius;

/**
 *
 * @author Sharon Mathys
 */
public class Ingredient {
    private String name;
    private double amount;
    // default unit string as empty string
    private String unit = "";

    // should make the unit an enum but not focused on that right now
    
    //Doing this without 

    public Ingredient(String name, double amount, String unit){
       this.name = name;
       this.amount = amount;
       this.unit = unit;
    }
    // default get Methods
    public String getName(){return name;}
    
    public double getAmount(){return amount;}
    
    public String getUnit(){return unit;}
    
    //default set Methods
    public void setName(String name){this.name=name;}
    
    public void setAmount(double amount){this.amount=amount;}
    
    public void setUnit(String unit){this.unit=unit;}

    // don't think need more than default at this time
    //Override the toString for a good printout
    @Override
    public String toString(){
        if (getUnit().equals("")){
            return this.amount+" "+this.name;
        }
        else{
            return this.amount+" "+this.unit+" "+this.name;
        }
    }
    
}
