package com.lucas.recipeapp.listeners;

import com.lucas.recipeapp.models.IngredientRecipeAPI;

import java.util.List;

public interface IngredientRecipeListener {
    void fetchedResponse(List<IngredientRecipeAPI> response, String message);
    void errorMessage(String error);

}
