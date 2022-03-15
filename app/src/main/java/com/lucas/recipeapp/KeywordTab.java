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

import com.lucas.recipeapp.adapters.KeywordRecipeAdapter;
import com.lucas.recipeapp.adapters.RecipeAdapter;
import com.lucas.recipeapp.data.ApiRequestManager;
import com.lucas.recipeapp.listeners.KeywordRecipeListener;
import com.lucas.recipeapp.listeners.RandomRecipeListener;
import com.lucas.recipeapp.models.KeywordRecipeAPI;
import com.lucas.recipeapp.models.RandomRecipeAPI;

public class KeywordTab extends Fragment {

    ApiRequestManager manager;
    RecipeAdapter recipeAdapter;
    KeywordRecipeAdapter keywordRecipeAdapter;
    SearchView keywordSearchView;
    RecyclerView keywordRecyclerView;

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
                manager.getKeywordRecipes(keywordRecipeListener, s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }


    private final KeywordRecipeListener keywordRecipeListener = new KeywordRecipeListener() {
        @Override
        public void fetchedResponse(KeywordRecipeAPI response, String message) {
            keywordRecyclerView = getView().findViewById(R.id.keywordsRecyclerView);
            keywordRecyclerView.setHasFixedSize(true);
            keywordRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
            keywordRecipeAdapter = new KeywordRecipeAdapter(getActivity(), response.results);
            keywordRecyclerView.setAdapter(keywordRecipeAdapter);
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
            recipeAdapter = new RecipeAdapter(getActivity(), response.recipes);
            keywordRecyclerView.setAdapter(recipeAdapter);
        }

        @Override
        public void errorMessage(String error) {
            Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
        }
    };
}