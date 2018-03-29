package com.example.alliebrenner.myapplication2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;


public class ResultActivity extends AppCompatActivity {
    private ListView myListView;
    private Context myContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
         myContext=this;

        //data to display
        final ArrayList<Recipe> recipeList = Recipe.getRecipesFromFile("recipes.json", this);

        //create adapter
        RecipeAdapter adapter = new RecipeAdapter(this, recipeList);

        myListView = findViewById(R.id.recipe_list_view);

        myListView.setAdapter(adapter);


    }
}