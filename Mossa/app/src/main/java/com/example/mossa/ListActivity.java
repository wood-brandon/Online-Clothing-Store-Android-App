package com.example.mossa;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    // local variables
    ListView lvClothes;
    ListAdapter ListAdapter;
    ArrayList<Clothing> sortedCatalogue;
    ArrayList<Clothing> aClothes;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                Intent intent = getIntent();
                String Query = intent.getStringExtra("SearchTerm").toLowerCase();

                setContentView(R.layout.activity_list);

                //setting parameters for custom action bar
                this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
                getSupportActionBar().setDisplayShowCustomEnabled(true);
                getSupportActionBar().setCustomView(R.layout.action_bar_nosearch);
                //set the colour of the action bar
                ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#a98274"));
                getSupportActionBar().setBackgroundDrawable(colorDrawable);

                // create listview using data provider
                lvClothes = (ListView) findViewById(R.id.main_list);
                lvClothes.setEmptyView(findViewById(R.id.empty_list_item));
                aClothes = MainActivity.getCatalogue();
                sortedCatalogue = new ArrayList<Clothing>();
                //create categoryCatalogue

                for (int i = 0; i < aClothes.size(); i++){

                    if (aClothes.get(i).getCategory().toLowerCase().equals(Query)){
                        sortedCatalogue.add(aClothes.get(i));
                    }
                    else if (aClothes.get(i).getName().toLowerCase().contains(Query)){
                        sortedCatalogue.add(aClothes.get(i));
                    }

                }

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

    public void viewDetail(View view){
        Intent intentdetail = new Intent(ListActivity.this, DetailsActivity.class);
    }
}