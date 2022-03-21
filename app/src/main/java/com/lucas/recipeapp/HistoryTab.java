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

import com.lucas.recipeapp.adapters.RecipeAdapter;
import com.lucas.recipeapp.data.ApiRequestManager;
import com.lucas.recipeapp.listeners.ClickedOnRecipeListener;
import com.lucas.recipeapp.listeners.RandomRecipeListener;
import com.lucas.recipeapp.models.RandomRecipeAPI;

public class HistoryTab extends Fragment {

    ApiRequestManager manager;
    RecipeAdapter recipeAdapter;
    SearchView keywordSearchView;
    RecyclerView keywordRecyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_layout, container, false);

        keywordRecyclerView = view.findViewById(R.id.keywordsRecyclerView);
        keywordSearchView = view.findViewById(R.id.keywordsSearchView);

        manager = new ApiRequestManager(getActivity());
        manager.getRandomRecipes(randomRecipeListener);

        return view;
    }

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
