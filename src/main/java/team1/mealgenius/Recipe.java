package team1.mealgenius;

/**
 *
 * @author Sharon
 */
public class Recipe {
    private String recipeName;
    private int recipeId;
    private Ingredient[] ingredients;
    private String directions; // one large string
    private String tags; // comma delimited string
    
    //Later versions can overload constructor.
    //currently allowing empty strings and arrays
    // overload with recipe_id (main use)
    public Recipe(String recipeName, Ingredient[] ingredients, 
            String directions, String tags){
        this.recipeName= recipeName;
        this.ingredients = ingredients;
        this.directions = directions;
        this.tags = tags;
    }
    public Recipe(String recipeName, int recipeId, Ingredient[] ingredients,
            String directions, String tags){
        this.recipeName = recipeName;
        this.recipeId = recipeId;
        this.ingredients = ingredients;
        this.directions = directions;
        this.tags = tags;
    }
    //set methods
    //Warning, recipename can be problematic!
    public void setRecipeName(String name){this.recipeName=name;}
    
    public void setRecipeId(int recipeId){this.recipeId = recipeId;}
    
    public void setIngredients(Ingredient[] list){this.ingredients = list;}
    
    public void setDirections(String directions){this.directions = directions;}
    
    public void setTags(String tags){this.tags= tags;}
    
    //get methods (default)
    public String getRecipeName(){return recipeName;}
    
    public int getRecipeId(){return recipeId;}
    
    public Ingredient[] getIngredients(){return ingredients;}
    
    public String getDirections(){return directions;}
    
    public String getTags(){return tags;}
    
    //
    
    
}
