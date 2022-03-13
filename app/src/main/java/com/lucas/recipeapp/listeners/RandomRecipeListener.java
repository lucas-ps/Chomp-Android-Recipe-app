package com.lucas.recipeapp.listeners;

import com.lucas.recipeapp.models.RandomRecipeAPI;

public interface RandomRecipeListener {
    void fetchedResponse(RandomRecipeAPI response, String message);
    void errorMessage(String error);
}
