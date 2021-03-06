package com.example.mossa;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Clothing> clothingCatalogue;
    public static ArrayList<Clothing> mostViewed;
    RecyclerView topPicks;
    private View rootView;

    public static ArrayList<Clothing> getCatalogue() {
        return clothingCatalogue;
    }

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
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        clothingCatalogue = Clothing.createClothingCatalogue(this);
        TopPicksAdapter adapter = new TopPicksAdapter(this, Clothing.createMostViewed(clothingCatalogue));
        //Setting up the recycler view
        topPicks = (RecyclerView) findViewById(R.id.top_picks);
        topPicks.setHasFixedSize(true);
        topPicks.setAdapter(adapter);
        topPicks.setLayoutManager(layoutManager);
        //click listener is set
        adapter.setOnItemClickListener(new TopPicksAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
                Clothing clickItem = TopPicksAdapter.getItem(position);
                clickItem.addView();
                intent.putExtra("Item",clickItem);
                startActivity(intent);
            }
        });

    }

    //Whenever the user returns to the Main activity the recycler view is updated
    @Override
    protected void onResume(){
        super.onResume();
        TopPicksAdapter adapter = new TopPicksAdapter(this, Clothing.createMostViewed(clothingCatalogue));
        //Setting up the recycler view
        topPicks = (RecyclerView) findViewById(R.id.top_picks);
        topPicks.setAdapter(adapter);


    }

    //when the action bar is created the root view requests focus inorder to stop the search view from
    //automatically gaining focus
    public boolean onCreateOptionsMenu(Menu menu){
        rootView = findViewById(R.id.root_view);
        final SearchView searchView = (SearchView) findViewById(R.id.action_search);
        searchView.setQuery("", false);
        rootView.requestFocus();
        //onQueryTextListener set for the searchView for when the user inputs data
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {

                //reset the searchView
                searchView.clearFocus();
                searchView.setQuery("", false);
                searchView.setIconified(true);

                //Intent is created sending the query to the ListActivity and starting the ListActivity
                Intent searchIntent = new Intent(MainActivity.this, ListActivity.class);
                searchIntent.putExtra("SearchTerm", query);
                startActivity(searchIntent);

                return true;

            }

            @Override
            public boolean onQueryTextChange(String s){
                return false;
            }
        });
        return true;
    }

    //Function for when Categories are clicked
    public void viewCategory(View view){
        Intent intentlist = new Intent(MainActivity.this, ListActivity.class);
        switch(view.getId()){
            case R.id.hats_catagory: intentlist.putExtra("Category", "Hats"); startActivity(intentlist);
                break;
            case R.id.pants_catagory: intentlist.putExtra("Category", "Pants"); startActivity(intentlist);
                break;
            case R.id.shirts_catagory: intentlist.putExtra("Category", "Shirts"); startActivity(intentlist);
                break;
             case R.id.shoes_catagory: intentlist.putExtra("Category", "Shoes"); startActivity(intentlist);
                break;

        }

    }

}
