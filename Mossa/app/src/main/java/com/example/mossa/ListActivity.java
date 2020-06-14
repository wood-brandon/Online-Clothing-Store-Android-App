package com.example.mossa;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    // local variables
    ListView lvClothes;
    TextView tvNoResults;
    TextView tvSearchTerm;
    ListAdapter ListAdapter;
    ArrayList<Clothing> sortedCatalogue;
    ArrayList<Clothing> aClothes;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                Intent intent = getIntent();
                // get search term or category filter
                String Query = intent.getStringExtra("SearchTerm");
                String Category = intent.getStringExtra("Category");

                setContentView(R.layout.activity_list);

                //setting parameters for custom action bar
                this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
                getSupportActionBar().setDisplayShowCustomEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setCustomView(R.layout.action_bar_nosearch);
                //set the colour of the action bar
                ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#a98274"));
                getSupportActionBar().setBackgroundDrawable(colorDrawable);

                // find views in activity_list.xml to populate
                lvClothes = (ListView) findViewById(R.id.main_list);
                lvClothes.setEmptyView(findViewById(R.id.empty_list_item));
                tvSearchTerm = (TextView) findViewById(R.id.Search_term);
                tvNoResults = (TextView) findViewById(R.id.empty_list_item);

                // show search term at top of screen if searched and populate no results found
                if (Category == null){


                    String SearchText = "Search results for \"" + Query + "\"";
                    tvSearchTerm.setText(SearchText);
                    String NotFound = "No results found for \"" + Query + "\".";
                    tvNoResults.setText(NotFound);
                } else {
                    tvSearchTerm.setVisibility(View.GONE);
                }


                // populate fields using list adapter
                aClothes = MainActivity.getCatalogue();
                // first, sort the main array by search term
                sortedCatalogue = new ArrayList<Clothing>();
                // check name for search term if that intent was passed
                if (Category == null) {
                    for (int i = 0; i < aClothes.size(); i++) {
                        if (aClothes.get(i).getName().toLowerCase().contains(Query.toLowerCase())) {
                            sortedCatalogue.add(aClothes.get(i));
                        }
                    }
                } else { // else check category
                    for (int i = 0; i < aClothes.size(); i++) {
                        if (aClothes.get(i).getCategory().toLowerCase().equals(Category.toLowerCase())) {
                            sortedCatalogue.add(aClothes.get(i));
                        }
                    }
                }

                if (sortedCatalogue.size() == 0){
                    tvSearchTerm.setVisibility(View.GONE);
                }
        // load adapter based on sorted list
        ListAdapter = new ListAdapter(this, sortedCatalogue);
        lvClothes.setAdapter(ListAdapter);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        setupItemSelectedListener();
    }


    public void setupItemSelectedListener() {
        lvClothes.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this,DetailsActivity.class);
                Clothing clickItem = ListAdapter.getItem(position);
                clickItem.addView();
                intent.putExtra("Item",clickItem);
                startActivity(intent);
            }
        });
    }


    public boolean onOptionsItemSelected(MenuItem item){

        finish();
        return true;
    }
}