package com.lucas.recipeapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.lucas.recipeapp.R;
import com.lucas.recipeapp.models.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class KeywordRecipeAdapter extends RecyclerView.Adapter {
    Context context;
    List<Result> recipeList;

    public KeywordRecipeAdapter(Context context, ArrayList<Result> results) {
        this.context = context;
        this.recipeList = results;
    }

    // Create viewholder on create
    @NonNull
    public KeywordRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new KeywordRecipeViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.list_recipe, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    // Set values on bind
    public void onBindViewHolder(@NonNull KeywordRecipeViewHolder holder, int position) {
        holder.textView_title.setText(recipeList.get(position).title);
        holder.textView_title.setSelected(true);
        holder.textView_servings.setText(recipeList.get(position).servings + " people");
        holder.textView_time.setText(recipeList.get(position).readyInMinutes + " minutes");
        Picasso.get().load(recipeList.get(position).image).into(holder.imageView_recipe);
    }

    // Return count of recipes
    public int getItemCount() {
        if (recipeList == null){
            return 0;
        }
        else return recipeList.size();
    }
}

class KeywordRecipeViewHolder extends RecyclerView.ViewHolder {
    CardView recipe_list_container;
    TextView textView_title;
    ImageView imageView_recipe;
    TextView textView_servings;
    TextView textView_time;

    public KeywordRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        recipe_list_container = itemView.findViewById(R.id.recipe_list_container);
        textView_title = itemView.findViewById(R.id.textView_title);
        imageView_recipe = itemView.findViewById(R.id.imageView_recipe);
        textView_servings = itemView.findViewById(R.id.textView_servings);
        textView_time = itemView.findViewById(R.id.textView_time);
    }
}
