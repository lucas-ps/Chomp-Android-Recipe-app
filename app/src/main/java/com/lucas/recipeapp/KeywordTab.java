package com.lucas.recipeapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.lucas.recipeapp.listeners.ClickedOnRecipeListener;
import com.lucas.recipeapp.listeners.KeywordRecipeListener;
import com.lucas.recipeapp.listeners.RandomRecipeListener;
import com.lucas.recipeapp.models.KeywordRecipeAPI;
import com.lucas.recipeapp.models.RandomRecipeAPI;

import java.util.Map;

public class KeywordTab extends Fragment {

    ApiRequestManager manager;
    RecipeAdapter recipeAdapter;
    KeywordRecipeAdapter keywordRecipeAdapter;
    SearchView keywordSearchView;
    RecyclerView keywordRecyclerView;
    public static final String SHARED_PREFS = "sharedprefs";

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
            keywordRecipeAdapter = new KeywordRecipeAdapter(getActivity(), response.results, clickedOnRecipeListener);
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
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Map<String, ?> all = sharedPreferences.getAll();

            if (!(sharedPreferences.contains(ID))){
                editor.remove(ID);
                editor.commit();
            }

            editor.putString(ID, String.valueOf(all.size()+1));
            editor.apply();
            startActivity(new Intent(getActivity(), RecipeDetailPage.class).putExtra("ID", ID));
        }
    };
}