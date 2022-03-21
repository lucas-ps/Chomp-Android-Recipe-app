package com.lucas.recipeapp.adapters;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lucas.recipeapp.R;
import com.lucas.recipeapp.models.ExtendedIngredient;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientViewHolder> {

    Context context;
    List<ExtendedIngredient> ingredientList;

    public IngredientAdapter(Context context, List<ExtendedIngredient> ingredientList) {
        this.context = context;
        this.ingredientList = ingredientList;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.list_ingredients, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        holder.textview_name_ingredient.setText(ingredientList.get(position).original);
        holder.textview_name_ingredient.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    public int getItemCount() {
        if (ingredientList == null){
            return 0;
        }
        else return ingredientList.size();
    }
}

class IngredientViewHolder extends RecyclerView.ViewHolder{
    TextView textview_name_ingredient;

    public IngredientViewHolder(@NonNull View itemView) {
        super(itemView);
        textview_name_ingredient = itemView.findViewById(R.id.textview_name_ingredient);
    }
}