package com.lucas.recipeapp.listeners;

import com.lucas.recipeapp.models.GetRecipeInstructionsAPI;

import java.util.List;

public interface RecipeInstructionsListener {
    void fetchedResponse(List<GetRecipeInstructionsAPI> response, String message);
    void errorMessage(String error);
}
