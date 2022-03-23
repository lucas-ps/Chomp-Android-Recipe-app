package com.lucas.recipeapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lucas.recipeapp.R;
import com.lucas.recipeapp.models.GetRecipeInstructionsAPI;
import com.lucas.recipeapp.models.Step;

import java.util.List;

public class InstructionsAdapter extends RecyclerView.Adapter<InstructionViewHolder> {

    Context context;
    List<GetRecipeInstructionsAPI> instructions;

    public InstructionsAdapter(Context context, List<GetRecipeInstructionsAPI> instructions) {
        this.context = context;
        this.instructions = instructions;
    }

    @NonNull
    @Override
    public InstructionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.list_instruction, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionViewHolder holder, int position) {
        //System.out.println("Step created");

        List<Step> steps= instructions.get(0).steps;

        holder.textView_instruction_title.setText("Step " +
                String.valueOf(steps.get(position).number) +":");
        holder.textView_instruction_content.setText(steps.get(position).step);

        /*holder.recyclerview_method.setHasFixedSize(true);
        holder.recyclerview_method.setLayoutManager(new StaggeredGridLayoutManager(1,
                LinearLayoutManager.VERTICAL));
        StepAdapter stepAdapter = new StepAdapter(context, instructions.get(position).steps);
        holder.recyclerview_method.setAdapter(stepAdapter);*/
    }

    @Override
    public int getItemCount() {
        //System.out.println(instructions.size());
        return instructions.get(0).steps.size();
    }
}

class InstructionViewHolder extends RecyclerView.ViewHolder {

    RecyclerView recyclerview_method;
    TextView textView_instruction_title;
    TextView textView_instruction_content;

    public InstructionViewHolder(@NonNull View itemView) {
        super(itemView);
        recyclerview_method = itemView.findViewById(R.id.recyclerview_method);
        textView_instruction_title = itemView.findViewById(R.id.textView_instruction_title);
        textView_instruction_content = itemView.findViewById(R.id.textView_instruction_content);

    }
}

