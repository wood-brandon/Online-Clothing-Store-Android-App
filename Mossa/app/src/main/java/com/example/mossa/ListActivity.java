package com.example.mossa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    // local variables
    ListView lvClothes;
    ListAdapter ListAdapter;
    ArrayList<Clothing> categoryCatalogue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String Category = intent.getStringExtra("Category");

        setContentView(R.layout.activity_list);



        // create listview using data provider
        lvClothes = (ListView) findViewById(R.id.main_list);

        ArrayList<Clothing> aClothes = MainActivity.clothingCatalogue;
        categoryCatalogue = new ArrayList<Clothing>();
        //create categoryCatalogue
        for (int i = 0; i < aClothes.size(); i++){

            if (aClothes.get(i).getCategory().equals(Category)){
                categoryCatalogue.add(aClothes.get(i));
            }

        }

        ListAdapter = new ListAdapter(this, categoryCatalogue);

        lvClothes.setAdapter(ListAdapter);
        LinearLayoutManager lm = new LinearLayoutManager(this);

        setupItemSelectedListener();
    }

    public void setupItemSelectedListener() {
        lvClothes.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this,DetailsActivity.class);
                intent.putExtra("Item",ListAdapter.getItem(position));
                startActivity(intent);
            }
        });
    }

    public void viewDetail(View view){
        Intent intentdetail = new Intent(ListActivity.this, DetailsActivity.class);
    }
}