package com.lucas.recipeapp.listeners;

import com.lucas.recipeapp.models.Result;

import java.util.List;

public interface InformationBulkListener {
    void fetchedResponse(List<Result> response, String message);
    void errorMessage(String error);
}
