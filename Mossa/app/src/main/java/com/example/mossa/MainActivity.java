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


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<Clothing> clothingCatalogue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting up the recycler view
        RecyclerView topPicks = (RecyclerView) findViewById(R.id.top_picks);
        topPicks.setHasFixedSize(true);
        clothingCatalogue = Clothing.createClothingCatalogue();
        TopPicksAdapter adapter = new TopPicksAdapter(clothingCatalogue);
        topPicks.setAdapter(adapter);
        topPicks.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    public void viewCategory(View view){

        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);

        switch(view.getId()){
            case R.id.hats_catagory: startActivity(intent);
            break;
            case R.id.pants_catagory: startActivity(intent);
            break;
            case R.id.shirts_catagory: startActivity(intent);
            break;
        }

    }
}
