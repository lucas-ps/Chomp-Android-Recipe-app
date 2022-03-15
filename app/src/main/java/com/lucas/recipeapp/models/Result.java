package com.lucas.recipeapp.models;

import java.util.ArrayList;

public class Result {
    public boolean vegetarian;
    public boolean vegan;
    public boolean glutenFree;
    public boolean dairyFree;
    public boolean veryHealthy;
    public boolean cheap;
    public boolean veryPopular;
    public boolean sustainable;
    public int weightWatcherSmartPoints;
    public String gaps;
    public boolean lowFodmap;
    public int aggregateLikes;
    public double spoonacularScore;
    public double healthScore;
    public String creditsText;
    public String license;
    public String sourceName;
    public double pricePerServing;
    public int id;
    public String title;
    public int readyInMinutes;
    public int servings;
    public String sourceUrl;
    public String image;
    public String imageType;
    public String summary;
    public ArrayList<String> cuisines;
    public ArrayList<String> dishTypes;
    public ArrayList<String> diets;
    public ArrayList<String> occasions;
    public ArrayList<AnalyzedInstruction> analyzedInstructions;
    public String spoonacularSourceUrl;

    public String getTitle(){
        return title;
    }
}
