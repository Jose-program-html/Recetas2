package shary.recetas.activity.rest;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Shary on 07/07/2015.
 */
public interface RecipeService {
    @GET("/recipes")
    public void getAllRecipes(Callback<List<Recipe>> cb);

    @GET("/ingredients")
    public void getAllIngredients(Callback<List<Ingredient>> cb);

    @GET("/instructions")
    public void getAllInstructions(Callback<List<Step>> cb);
}
