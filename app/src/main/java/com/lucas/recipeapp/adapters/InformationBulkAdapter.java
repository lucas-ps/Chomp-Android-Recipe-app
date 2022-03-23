package com.lucas.recipeapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lucas.recipeapp.R;
import com.lucas.recipeapp.listeners.ClickedOnRecipeListener;
import com.lucas.recipeapp.models.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InformationBulkAdapter extends RecyclerView.Adapter<RecipeViewHolder> {
    Context context;
    List<Result> recipeList;
    ClickedOnRecipeListener listener;

    public InformationBulkAdapter(Context context, List<Result> recipes, ClickedOnRecipeListener listener) {
        this.context = context;
        this.recipeList = recipes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecipeViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.list_recipe, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        holder.textView_title.setText(recipeList.get(position).title);
        holder.textView_title.setSelected(true);
        holder.textView_servings.setText(recipeList.get(position).servings + " people");
        holder.textView_time.setText(recipeList.get(position).readyInMinutes + " minutes");
        Picasso.get().load(recipeList.get(position).image).into(holder.imageView_recipe);

        holder.recipe_list_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickRecipe(String.valueOf(recipeList.get(holder.getAdapterPosition()).id));
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }
}

