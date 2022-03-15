package com.lucas.recipeapp.listeners;
import com.lucas.recipeapp.models.KeywordRecipeAPI;

public interface KeywordRecipeListener {
    void fetchedResponse(KeywordRecipeAPI response, String message);
    void errorMessage(String error);

}
