package com.lucas.recipeapp.data;

import android.content.Context;

import com.lucas.recipeapp.listeners.InformationBulkListener;
import com.lucas.recipeapp.listeners.KeywordRecipeListener;
import com.lucas.recipeapp.listeners.RandomRecipeListener;
import com.lucas.recipeapp.listeners.RecipeInfoListener;
import com.lucas.recipeapp.listeners.RecipeInstructionsListener;
import com.lucas.recipeapp.models.GetRecipeInfoAPI;
import com.lucas.recipeapp.models.GetRecipeInstructionsAPI;
import com.lucas.recipeapp.models.KeywordRecipeAPI;
import com.lucas.recipeapp.models.RandomRecipeAPI;
import com.lucas.recipeapp.models.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiRequestManager {
    Context context;
    Retrofit retrofit = RetrofitClient.getRetrofitClient();
    CallApiManager api = RetrofitClient.getApi();

    public ApiRequestManager(Context context) {
        this.context = context;
    }

    public void getRandomRecipes(RandomRecipeListener listener) {
        Call<RandomRecipeAPI> call = api.callRandomRecipeAPI("10");
        call.enqueue(new Callback<RandomRecipeAPI>() {
            @Override
            public void onResponse(Call<RandomRecipeAPI> call, Response<RandomRecipeAPI> response) {
                if (!response.isSuccessful()) {
                    listener.errorMessage(response.message());
                    return;
                }
                listener.fetchedResponse(response.body(), response.message());
                //System.out.println("Fetched random recipes successfully");
            }

            @Override
            public void onFailure(Call<RandomRecipeAPI> call, Throwable t) {
                listener.errorMessage(t.getMessage());
            }
        });
    }

    public void getKeywordRecipes (KeywordRecipeListener listener, String query) {
        Call<KeywordRecipeAPI> call = api.callKeywordRecipeAPI(query, "true");
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

    public void getRecipeInfo(RecipeInfoListener listener, int ID) {
        Call<GetRecipeInfoAPI> call =
                api.callRecipeInfoAPI(ID, "false");
        call.enqueue(new Callback<GetRecipeInfoAPI>() {
            @Override
            public void onResponse(Call<GetRecipeInfoAPI> call, Response<GetRecipeInfoAPI> response) {
                if (!response.isSuccessful()) {
                    listener.errorMessage(response.message());
                    System.out.println("Error while fetching recipe info for ID: "+ ID);
                    return;
                }
                listener.fetchedResponse(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<GetRecipeInfoAPI> call, Throwable t) {
                listener.errorMessage(t.getMessage());
            }
        });
    }

    public void getRecipeInstructions(RecipeInstructionsListener listener, int ID) {
        Call<List<GetRecipeInstructionsAPI>> call = api.callRecipeInstructionsAPI(ID);
        call.enqueue(new Callback<List<GetRecipeInstructionsAPI>>() {
            @Override
            public void onResponse(Call<List<GetRecipeInstructionsAPI>> call, Response<List<GetRecipeInstructionsAPI>> response) {
                if (!response.isSuccessful()) {
                    listener.errorMessage(response.message());
                    System.out.println("Error while fetching recipe info for ID: "+ ID);
                    return;
                }
                listener.fetchedResponse(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<GetRecipeInstructionsAPI>> call, Throwable t) {
                listener.errorMessage(t.getMessage());
            }
        });
    }

    public void getInformationBulkAPI(InformationBulkListener listener, String IDs){
        Call<List<Result>> call = api.callinformationBulkAPI(IDs, "false");
        call.enqueue(new Callback<List<Result>>() {
            @Override
            public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                if (!response.isSuccessful()) {
                    listener.errorMessage(response.message());
                    System.out.println("Error while fetching recipe info for IDs: "+ IDs);
                    return;
                }
                listener.fetchedResponse(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<Result>> call, Throwable t) {
                listener.errorMessage(t.getMessage());
            }
        });
    }
}
