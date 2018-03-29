package com.example.alliebrenner.myapplication2;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class Recipe {

    //instance variables

    public String title;
    public String imageUrl;
    public String instructionUrl;
    public String description;
    public int servings;
    public String prepTime;
    public String dietLabel;

    public static ArrayList<Recipe> getRecipesFromFile(String filename, Context context){
        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();

        //standard json parser
        //try to read from json file
        //get info by using the tags
        //construct a recipe object for each recipe in json
        //add object to arraylist
        //return arraylist
        try{
            String jsonString = loadJsonFromAsset("recipes.json",context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray recipes = json.getJSONArray("recipes");
            //for loop to go through each recipe in recipes array
            for(int i = 0; i< recipes.length();i++){
                Recipe recipe = new Recipe();
                recipe.title = recipes.getJSONObject(i).getString("title");
                recipe.description= recipes.getJSONObject(i).getString("description");
                recipe.servings = recipes.getJSONObject(i).getInt("servings");
                recipe.prepTime = recipes.getJSONObject(i).getString("prepTime");
                recipe.imageUrl = recipes.getJSONObject(i).getString("image");
                recipe.instructionUrl = recipes.getJSONObject(i).getString("url");
                recipe.dietLabel = recipes.getJSONObject(i).getString("dietLabel");

                //add to arraylist
                recipeList.add(recipe);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return recipeList;
    }

    // helper method that loads from any Json file

    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return imageUrl;
    }

    public void setImage(String image) {
        this.imageUrl = image;
    }

    public String getUrl() {
        return instructionUrl;
    }

    public void setUrl(String url) {
        this.instructionUrl = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(String prepTime) {
        this.prepTime = prepTime;
    }

    public String getDietLabel() {
        return dietLabel;
    }

    public void setDiet(String dietLabel) {
        this.dietLabel = dietLabel;
    }

}


