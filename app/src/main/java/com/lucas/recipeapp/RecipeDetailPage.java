package com.lucas.recipeapp;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lucas.recipeapp.adapters.IngredientAdapter;
import com.lucas.recipeapp.adapters.InstructionsAdapter;
import com.lucas.recipeapp.data.ApiRequestManager;
import com.lucas.recipeapp.listeners.RecipeInfoListener;
import com.lucas.recipeapp.listeners.RecipeInstructionsListener;
import com.lucas.recipeapp.models.GetRecipeInfoAPI;
import com.lucas.recipeapp.models.GetRecipeInstructionsAPI;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeDetailPage extends AppCompatActivity {

    int ID;
    TextView textview_recipe_name;
    TextView textview_link;
    ImageView imageview_recipe_image;
    RecyclerView recyclerview_ingredients;
    ApiRequestManager manager;
    Dialog dialog;
    IngredientAdapter ingredientAdapter;
    RecyclerView recyclerview_method;
    InstructionsAdapter instructionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_detail_page);

        ID = Integer.parseInt(getIntent().getStringExtra("ID"));
        textview_recipe_name = findViewById(R.id.textview_recipe_name);
        imageview_recipe_image = findViewById(R.id.imageview_recipe_image);
        recyclerview_ingredients = findViewById(R.id.recyclerview_ingredients);
        textview_link = findViewById(R.id.textview_link);
        recyclerview_method = findViewById(R.id.recyclerview_method);

        manager = new ApiRequestManager(this);
        manager.getRecipeInfo(recipeInfoListener, ID);
        manager.getRecipeInstructions(recipeInstructionsListener, ID);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading recipe...");
        dialog.show();
    }

    private RecipeInstructionsListener recipeInstructionsListener = new RecipeInstructionsListener() {
        @Override
        public void fetchedResponse(List<GetRecipeInstructionsAPI> response, String message) {
            dialog.dismiss();

            recyclerview_method.setLayoutManager(new LinearLayoutManager(
                    RecipeDetailPage.this, LinearLayoutManager.VERTICAL,
                    false));
            recyclerview_method.setHasFixedSize(true);
            instructionsAdapter = new InstructionsAdapter(RecipeDetailPage.this, response);
            recyclerview_method.setAdapter(instructionsAdapter);
            //System.out.println("Instructions fetched successfully");
        }

        @Override
        public void errorMessage(String error) {
            Toast.makeText(RecipeDetailPage.this, error, Toast.LENGTH_SHORT).show();
        }
    };

    private RecipeInfoListener recipeInfoListener = new RecipeInfoListener() {
        @Override
        public void fetchedResponse(GetRecipeInfoAPI response, String message) {
            textview_recipe_name.setText(response.title);

            //System.out.println(response.sourceUrl);
            textview_link.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(response.sourceUrl));
                    startActivity(i);
                }
            });
            Picasso.get().load(response.image).into(imageview_recipe_image);

            recyclerview_ingredients.setLayoutManager(new LinearLayoutManager(
                    RecipeDetailPage.this, LinearLayoutManager.HORIZONTAL,
                    false));
            recyclerview_ingredients.setHasFixedSize(true);
            ingredientAdapter = new IngredientAdapter(RecipeDetailPage.this,
                    response.extendedIngredients);
            recyclerview_ingredients.setAdapter(ingredientAdapter);
        }

        @Override
        public void errorMessage(String error) {
            Toast.makeText(RecipeDetailPage.this, error, Toast.LENGTH_SHORT).show();
        }
    };
}