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
import com.lucas.recipeapp.models.IngredientRecipeAPI;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IngredientRecipeAdapter extends RecyclerView.Adapter<IngredientRecipeViewHolder> {
    Context context;
    List<IngredientRecipeAPI> recipeList;

    public IngredientRecipeAdapter(Context context, List<IngredientRecipeAPI> recipes){
        this.context = context;
        this.recipeList = recipes;
    }

    // Create viewholder on create
    @NonNull
    public IngredientRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientRecipeViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.list_recipe, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull IngredientRecipeViewHolder holder, int position) {
        System.out.println(recipeList.get(position).title);

        holder.textView_title.setText(recipeList.get(position).title);
        holder.textView_title.setSelected(true);

        int usedIngredients = recipeList.get(position).usedIngredientCount;
        int missedIngredients = recipeList.get(position).missedIngredientCount;

        int ratio = usedIngredients/(usedIngredients + missedIngredients) * 100;

        holder.textView_servings.setText(ratio + "% ingredient match");
        holder.textView_time.setText(ratio + "% ingredient match");
        Picasso.get().load(recipeList.get(position).image).into(holder.imageView_recipe);
    }

    @Override
    public int getItemCount() {
        if (recipeList == null){
            return 0;
        }
        else return recipeList.size();
    }
}

class IngredientRecipeViewHolder extends RecyclerView.ViewHolder {
    CardView recipe_list_container;
    TextView textView_title;
    ImageView imageView_recipe;
    TextView textView_servings;
    TextView textView_time;

    public IngredientRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        recipe_list_container = itemView.findViewById(R.id.recipe_list_container);
        textView_title = itemView.findViewById(R.id.textView_title);
        imageView_recipe = itemView.findViewById(R.id.imageView_recipe);
        textView_servings = itemView.findViewById(R.id.textView_servings);
        textView_time = itemView.findViewById(R.id.textView_time);
    }
}
