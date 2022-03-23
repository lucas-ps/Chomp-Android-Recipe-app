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

import com.lucas.recipeapp.adapters.InformationBulkAdapter;
import com.lucas.recipeapp.data.ApiRequestManager;
import com.lucas.recipeapp.listeners.ClickedOnRecipeListener;
import com.lucas.recipeapp.listeners.InformationBulkListener;
import com.lucas.recipeapp.models.Result;

import java.util.List;
import java.util.Map;

public class HistoryTab extends Fragment {

    ApiRequestManager manager;
    InformationBulkAdapter recipeAdapter;
    SearchView keywordSearchView;
    RecyclerView keywordRecyclerView;
    public static final String SHARED_PREFS = "sharedprefs";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        Map<String, ?> all = sharedPreferences.getAll();

        String recipes = "";

        for (String i : all.keySet()) {
            recipes += i+",";
        }

        System.out.println(recipes);

        View view = inflater.inflate(R.layout.search_layout, container, false);

        keywordRecyclerView = view.findViewById(R.id.keywordsRecyclerView);
        keywordSearchView = view.findViewById(R.id.keywordsSearchView);

        manager = new ApiRequestManager(getActivity());
        manager.getInformationBulkAPI(informationBulkListener, recipes);

        return view;
    }

    private final InformationBulkListener informationBulkListener = new InformationBulkListener() {
        @Override
        public void fetchedResponse(List<Result> response, String message) {
            keywordRecyclerView = getView().findViewById(R.id.keywordsRecyclerView);
            keywordRecyclerView.setHasFixedSize(true);
            keywordRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
            recipeAdapter = new InformationBulkAdapter(getActivity(), response, clickedOnRecipeListener);
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
            startActivity(new Intent(getActivity(), RecipeDetailPage.class).putExtra("ID", ID));
        }
    };

}
