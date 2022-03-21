package com.lucas.recipeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lucas.recipeapp.adapters.IngredientRecipeAdapter;
import com.lucas.recipeapp.adapters.RecipeAdapter;
import com.lucas.recipeapp.data.ApiRequestManager;
import com.lucas.recipeapp.listeners.ClickedOnRecipeListener;
import com.lucas.recipeapp.listeners.IngredientRecipeListener;
import com.lucas.recipeapp.listeners.RandomRecipeListener;
import com.lucas.recipeapp.models.IngredientRecipeAPI;
import com.lucas.recipeapp.models.RandomRecipeAPI;

import java.util.List;

public class IngredientTab extends Fragment {

    ApiRequestManager manager;
    RecipeAdapter recipeAdapter;
    SearchView keywordSearchView;
    RecyclerView keywordRecyclerView;
    IngredientRecipeAdapter ingredientRecipeAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_layout, container, false);

        keywordRecyclerView = view.findViewById(R.id.keywordsRecyclerView);
        keywordSearchView = view.findViewById(R.id.keywordsSearchView);

        manager = new ApiRequestManager(getActivity());
        manager.getRandomRecipes(randomRecipeListener);

        setupSearchView();

        return view;
    }

    private void setupSearchView() {
        keywordSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                String commaSeparatedIngredients = s.replaceAll("\\s*,\\s*", ",");
                manager.getIngredientRecipes(ingredientRecipeListener, commaSeparatedIngredients);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    private final IngredientRecipeListener ingredientRecipeListener = new IngredientRecipeListener() {
        @Override
        public void fetchedResponse(List<IngredientRecipeAPI> response, String message) {
            keywordRecyclerView = getView().findViewById(R.id.keywordsRecyclerView);
            keywordRecyclerView.setHasFixedSize(true);
            keywordRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
            ingredientRecipeAdapter = new IngredientRecipeAdapter(getActivity(), response);
            keywordRecyclerView.setAdapter(recipeAdapter);
        }

        @Override
        public void errorMessage(String error) {
            Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
        }
    };

    private final RandomRecipeListener randomRecipeListener = new RandomRecipeListener() {
        @Override
        public void fetchedResponse(RandomRecipeAPI response, String message) {
            keywordRecyclerView = getView().findViewById(R.id.keywordsRecyclerView);
            keywordRecyclerView.setHasFixedSize(true);
            keywordRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
            recipeAdapter = new RecipeAdapter(getActivity(), response.recipes, clickedOnRecipeListener);
            keywordRecyclerView.setAdapter(recipeAdapter);
        }

        @Override
        public void errorMessage(String error) {
            Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
        }
    };

    private final ClickedOnRecipeListener clickedOnRecipeListener =  new ClickedOnRecipeListener() {
        @Override
        public void onClickRecipe(String ID) {

        }
    };

}
