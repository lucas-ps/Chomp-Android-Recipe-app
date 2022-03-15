package com.lucas.recipeapp.models;

import java.util.ArrayList;


public class KeywordRecipeAPI{
    public ArrayList<Result> results;
    public int offset;
    public int number;
    public int totalResults;

    public String toString(){
        StringBuilder s = null;
        for (Result k : results){
            s.append(k.getTitle());
        }
        return s.toString();
    }
}


