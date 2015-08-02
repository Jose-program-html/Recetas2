package shary.recetas.activity.SQLite;

import android.content.Context;
import android.util.Log;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import shary.recetas.activity.rest.Ingredient;
import shary.recetas.activity.rest.Recipe;
import shary.recetas.activity.rest.RecipeService;
import shary.recetas.activity.rest.Step;

/**
 * Created by Shary on 14/07/2015.
 */
public class Records {
    private RecipeService recipeService;
    private static final String TAG = "Recipe List Activity";
    public Context context;
    private RestAdapter restAdapter;
    ColumnsTable columnas = new ColumnsTable();

    public Records(Context contexto, String endpoint) {
        this.context = contexto;
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .build();

        getAllRecipes();
        getAllIngredients();
        getAllInstructions();
    }


    private void getAllRecipes() {
        recipeService = restAdapter.create(RecipeService.class);
        recipeService.getAllRecipes(new Callback<List<Recipe>>() {
            @Override
            public void success(List<Recipe> recipes, Response response) {
                String cadena = "";
                Querys querys = new Querys(context, "recipe");
                querys.truncate();
                for (int i = 0; i < recipes.size(); i++) {
                    cadena = recipes.get(i).getId() + "-" + recipes.get(i).getNombre()
                            + "-" + recipes.get(i).getTipo() + "-" + recipes.get(i).getCategoria()
                            + "-" + recipes.get(i).getDuracion() + "-" + recipes.get(i).getPorcion() + "-" +
                            recipes.get(i).getNombreimagen() + "-" + recipes.get(i).getBase64() + "-" + "1";

                    querys.insertar(columnas.getColumnsTableRecipe(), cadena.toUpperCase());
                    System.out.println("CADENA RECETA " + cadena);
                }

            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());
                System.out.println("No se pudo obtener el Listado de recetas");
            }
        });
    }

    private void getAllIngredients() {
        recipeService = restAdapter.create(RecipeService.class);
        recipeService.getAllIngredients(new Callback<List<Ingredient>>() {
            @Override
            public void success(List<Ingredient> ingredients, Response response) {
                String cadena = "";
                Querys querys = new Querys(context, "ingredients");
                querys.truncate();
                for (int i = 0; i < ingredients.size(); i++) {
                    cadena = ingredients.get(i).getId() + "-" + ingredients.get(i).getNombre()
                            + "-" + ingredients.get(i).getCantidad() + "-" + ingredients.get(i).getClasificacion()
                            + "-" + ingredients.get(i).getIdRecipeIngredient();

                    querys.insertar(columnas.getColumnsTableIngredients(), cadena.toUpperCase());
                    System.out.println("CADENA INGREDIENTE " + cadena);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("No se pudo obtener el Listado de Ingredientes");
            }
        });
    }

    private void getAllInstructions() {
        recipeService = restAdapter.create(RecipeService.class);
        recipeService.getAllInstructions(new Callback<List<Step>>() {
            @Override
            public void success(List<Step> steps, Response response) {
                String cadena = "";
                Querys querys = new Querys(context, "step");
                querys.truncate();
                for (int i = 0; i < steps.size(); i++) {
                    cadena = steps.get(i).getId() + "-" + steps.get(i).getPaso()
                            + "-" + steps.get(i).getPaso_descripcion() + "-" + steps.get(i).getIdRecipe_step();

                    querys.insertar(columnas.getColumnsTableStep(), cadena.toUpperCase());
                    System.out.println("CADENA STEP " + cadena);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("No se pudo obtener el Listado de Pasos");
            }
        });
    }
}
