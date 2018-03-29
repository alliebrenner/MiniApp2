package com.example.alliebrenner.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Button toActivityThree = findViewById(R.id.find_recipes);
        toActivityThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, ResultActivity.class);
                startActivity(intent);
            }
            public ArrayList<String> uniquePrepTimes (ArrayList<Recipe> recipeList){
                ArrayList<String> prepOptions = new ArrayList<>();
                for (int i =0; i <recipeList.size();i++){
                    String prep = recipeList.get(i).prepTime;
                    if (prepOptions.contains(prep)){
                        prepOptions.add(prep);
                    }
                }
            return prepOptions;
            }
            public ArrayList<String> uniqueServings (ArrayList<Recipe> recipeList){
                ArrayList<String> servingOptions = new ArrayList<>();
                for (int i =0; i <recipeList.size();i++){
                   String serving = Integer.toString(recipeList.get(i).servings);
                    if (servingOptions.contains(serving)){
                        servingOptions.add(serving);
                    }
                }
                return servingOptions;
            }

            public ArrayList<String> uniqueDietOptions (ArrayList<Recipe> recipeList){
                ArrayList<String> dietOptions = new ArrayList<>();
                for (int i =0; i <recipeList.size();i++){
                    String dietLabel = (recipeList.get(i).dietLabel);
                    if (dietOptions.contains(dietLabel)){
                        dietOptions.add(dietLabel);
                    }
                }
                return dietOptions;
            }

            public HashMap<String,ArrayList<String>>makePrepMap(ArrayList<Recipe> recipeList){
                    ArrayList<String>prepOptions = uniquePrepTimes(recipeList);

                ArrayList<String> thirtyLess = new ArrayList<>();
                ArrayList<String> OneHourLess = new ArrayList<>();
                ArrayList<String> OneHourPlus = new ArrayList<>();

                for (int i = 0; i < prepOptions.size();i++){
                    String currPrepTime = prepOptions.get(i);
                    String[] words = currPrepTime.split(" ");
                    if (words[1].equals("minutes")){
                        OneHourLess.add(currPrepTime);
                        if (Integer.parseInt(words[0]) <= 30){
                            thirtyLess.add(currPrepTime);

                        }
                    }
                    else {
                        OneHourPlus.add(currPrepTime);
                    }

                }
                HashMap<String,ArrayList<String>> prepMap = new HashMap<>();
                prepMap.put("less than 30 minutes", thirtyLess);
                prepMap.put("less than 1 hour", OneHourLess);
                prepMap.put("more than 1 hour", OneHourPlus);
                return prepMap;
            }

           /* public HashMap<String ,ArrayList<String>>makeServingMap(ArrayList<Recipe> recipeList){
                ArrayList<String> servingOptions = uniqueServings(recipeList);

                ArrayList<String> tenPlus = new ArrayList<>();
                ArrayList<String> fourLess = new ArrayList<>();
                ArrayList<String> fourToSix = new ArrayList<>();
                ArrayList<String> sevenToNine = new ArrayList<>();


                HashMap<String,ArrayList<String>> servingMap = new HashMap<>();
                servingMap.put("less than 4",fourLess);
                servingMap.put("4-6", fourToSix);
                servingMap.put("7-9", sevenToNine);
                servingMap.put("more than 10", tenPlus);

                for (int i = 0; i <servingOptions.size(); i++){
                    String currServing = servingOptions.get(i);
                    String[] nums = currServing.split(" ");
                    if (Integer.parseInt(nums[0])>= 10){
                        tenPlus.add(currServing);

                        if (Integer.parseInt(nums[0])<4){
                        fourLess.add(currServing);
                        }

                        if (Integer.parseInt(nums[0])=<6 && >= 4){
                        fourToSix.add(currServing);

                        }
                        if (Integer.parseInt(nums[0])>=7 && =<9){
                        sevenToNine.add(currServing)
                        }}
                    else {
                        tenPlus.add(currServing);
                    }


                }
                return servingMap;


        }

            public HashMap<String, ArrayList<String>>makeDietMap(ArrayList<Recipe> recipeList){
                    ArrayList<String> dietOptions = uniqueDietOptions (recipeList);

                    ArrayList<String> lowCarb = new ArrayList<>();
                    ArrayList<String> lowSodium = new ArrayList<>();
                    ArrayList<String> lowFat = new ArrayList<>();
                    ArrayList<String>  mediumCarb = new ArrayList<>();
                    ArrayList<String> vegetarian = new ArrayList<>();
                    ArrayList<String> highCarb = new ArrayList<>();
                    ArrayList<String> balanced = new ArrayList<>();

                HashMap<String,ArrayList<String>> dietMap = new HashMap<>();
                dietMap.put("Low-Carb",lowCarb);
                dietMap.put("Low-Sodium", lowSodium);
                dietMap.put("Low-Fat",lowFat);
                dietMap.put("Medium-Carb",mediumCarb);
                dietMap.put("Vegetarian",vegetarian);
                dietMap.put("High-Carb",highCarb);
                dietMap.put("Balanced",balanced);

                for (int i = 0; i <dietOptions.size();i++){
                    String currDietLabel = dietOptions.get(i);
                    String[]words = currDietLabel.split(" ");
                    if (words.equals("Low-Carb")){
                        lowCarb.add(currDietLabel);
                    }
                    if (words.equals("Low-Sodium")){
                        lowSodium.add(currDietLabel);
                    }
                    if (words.equals("Low-Fat")){
                        lowFat.add(currDietLabel);
                    }
                    if (words.equals("Medium-Carb")){
                        mediumCarb.add(currDietLabel);
                    }
                    if (words.equals("High-Carb")){
                        highCarb.add(currDietLabel);
                    }
                    if (words.equals("Vegetarian")){
                        vegetarian.add(currDietLabel);
                    }
                    else {
                        balanced.add(currDietLabel);
                    }
                }
                return dietMap;

            }*/
    });
}}