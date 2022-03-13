package com.lucas.recipeapp.data;

import android.content.Context;

import com.lucas.recipeapp.R;
import com.lucas.recipeapp.listeners.RandomRecipeListener;
import com.lucas.recipeapp.models.RandomRecipeAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiRequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create()).build();

    public ApiRequestManager(Context context) {
        this.context = context;
    }

    private interface callRandomRecipes {
        @GET("recipes/random")
        Call<RandomRecipeAPI> callRandomRecipeAPI(
                @Query("apiKey") String apiKey,
                @Query("number") String number);
    }

    public void getRandomRecipes(RandomRecipeListener listener) {
        callRandomRecipes callRandom = retrofit.create(callRandomRecipes.class);
        Call<RandomRecipeAPI> call = callRandom.callRandomRecipeAPI(context.getString(R.string.api_key), "10");
        call.enqueue(new Callback<RandomRecipeAPI>() {
            @Override
            public void onResponse(Call<RandomRecipeAPI> call, Response<RandomRecipeAPI> response) {
                if (!response.isSuccessful()) {
                    listener.errorMessage(response.message());
                    return;
                }
                listener.fetchedResponse(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RandomRecipeAPI> call, Throwable t) {
                listener.errorMessage(t.getMessage());
            }
        });
    }
}
