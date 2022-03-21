package com.lucas.recipeapp.listeners;

import com.lucas.recipeapp.models.GetRecipeInfoAPI;

public interface RecipeInfoListener {
    void fetchedResponse(GetRecipeInfoAPI response, String message);
    void errorMessage(String error);
}
