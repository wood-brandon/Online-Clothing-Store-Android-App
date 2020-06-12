package com.example.mossa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;


import java.util.ArrayList;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Clothing> clothingCatalogue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
