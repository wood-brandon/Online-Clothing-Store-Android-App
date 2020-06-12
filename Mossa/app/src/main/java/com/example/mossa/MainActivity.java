package com.example.mossa;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.ListView;


import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    public static ArrayList<Clothing> clothingCatalogue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting parameters for custom action bar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar);
        //set the colour of the action bar
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#a98274"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        //defining parameters for recycler view
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        clothingCatalogue = Clothing.createClothingCatalogue(this);
        TopPicksAdapter adapter = new TopPicksAdapter(this, clothingCatalogue);
        //Setting up the recycler view
        RecyclerView topPicks = (RecyclerView) findViewById(R.id.top_picks);
        topPicks.setHasFixedSize(true);
        topPicks.setAdapter(adapter);
        topPicks.setLayoutManager(layoutManager);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        final SearchView searchView = (SearchView) findViewById(R.id.action_search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {

                searchView.clearFocus();
                searchView.setQuery("", false);
                searchView.setIconified(true);



                MainActivity.this.setTitle(query);
                return true;

            }

            @Override
            public boolean onQueryTextChange(String s){
                return false;
            }
        });
        return true;
    }



    public void viewCategory(View view){

        Intent intentdetail = new Intent(MainActivity.this, DetailsActivity.class);
        Intent intentlist = new Intent(MainActivity.this, ListActivity.class);

        switch(view.getId()){
            case R.id.hats_catagory: intentlist.putExtra("Category", "Hats"); startActivity(intentlist);
                break;
            case R.id.pants_catagory: intentlist.putExtra("Category", "Pants"); startActivity(intentlist);
                break;
            case R.id.shirts_catagory: intentlist.putExtra("Category", "Shirts"); startActivity(intentlist);
                break;
        }

    }
}
