package com.lucas.recipeapp.data;

import android.content.Context;

import com.lucas.recipeapp.listeners.KeywordRecipeListener;
import com.lucas.recipeapp.listeners.RandomRecipeListener;
import com.lucas.recipeapp.models.KeywordRecipeAPI;
import com.lucas.recipeapp.models.RandomRecipeAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiRequestManager {
    Context context;
    Retrofit retrofit = RetrofitClient.getRetrofitClient();


    public ApiRequestManager(Context context) {
        this.context = context;
    }

    private interface callRandomRecipes {
        @GET("recipes/random")
        Call<RandomRecipeAPI> callRandomRecipeAPI(
                @Query("number") String number);
    }

    public void getRandomRecipes(RandomRecipeListener listener) {
        callRandomRecipes callRandom = retrofit.create(callRandomRecipes.class);
        Call<RandomRecipeAPI> call = callRandom.callRandomRecipeAPI("10");
        call.enqueue(new Callback<RandomRecipeAPI>() {
            @Override
            public void onResponse(Call<RandomRecipeAPI> call, Response<RandomRecipeAPI> response) {
                if (!response.isSuccessful()) {
                    listener.errorMessage(response.message());
                    return;
                }
                listener.fetchedResponse(response.body(), response.message());
                System.out.println("Fetched random recipes successfully");
            }

            @Override
            public void onFailure(Call<RandomRecipeAPI> call, Throwable t) {
                listener.errorMessage(t.getMessage());
            }
        });
    }

    private interface callKeywordRecipes {
        @GET("recipes/complexSearch")
        Call<KeywordRecipeAPI> callKeywordRecipeAPI(
                @Query("query") String query,
                @Query("addRecipeInformation") String information);
    }

    public void getKeywordRecipes(KeywordRecipeListener listener, String query) {
        callKeywordRecipes callKeyword = retrofit.create(callKeywordRecipes.class);
        Call<KeywordRecipeAPI> call = callKeyword.callKeywordRecipeAPI(query, "true");
        call.enqueue(new Callback<KeywordRecipeAPI>() {
            @Override
            public void onResponse(Call<KeywordRecipeAPI> call, Response<KeywordRecipeAPI> response) {
                if (!response.isSuccessful()) {
                    listener.errorMessage(response.message());
                    System.out.println("Error while fetching recipes by keyword");
                    return;
                }
                listener.fetchedResponse(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<KeywordRecipeAPI> call, Throwable t) {
                listener.errorMessage(t.getMessage());
            }
        });
    }
}
