package com.lucas.recipeapp.data;

import com.lucas.recipeapp.models.GetRecipeInfoAPI;
import com.lucas.recipeapp.models.GetRecipeInstructionsAPI;
import com.lucas.recipeapp.models.IngredientRecipeAPI;
import com.lucas.recipeapp.models.KeywordRecipeAPI;
import com.lucas.recipeapp.models.RandomRecipeAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CallApiManager {

    @GET("recipes/findByIngredients")
    Call<List<IngredientRecipeAPI>> callIngredientRecipeAPI(
            @Query("ingredients") String ingredients,
            @Query("number") String number,
            @Query("ignorePantry") String pantry);

    @GET("recipes/random")
    Call<RandomRecipeAPI> callRandomRecipeAPI(
            @Query("number") String number);

    @GET("recipes/complexSearch")
    Call<KeywordRecipeAPI> callKeywordRecipeAPI(
            @Query("query") String query,
            @Query("addRecipeInformation") String information);

    @GET("recipes/{id}/information")
    Call<GetRecipeInfoAPI> callRecipeInfoAPI(
            @Path("id") int ID,
            @Query("includeNutrition") String nutrition);

    @GET("/recipes/{id}/analyzedInstructions")
    Call<List<GetRecipeInstructionsAPI>> callRecipeInstructionsAPI(
            @Path("id") int ID
    );

}

