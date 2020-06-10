package com.example.mossa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.content.Intent;
import android.os.Bundle;

//Glide imports
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

public class MainActivity extends AppCompatActivity {

    //Glide Module
    @GlideModule
    public final class MyAppGlideModule extends AppGlideModule{

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //setting up the recycler view
        ImageView test = (ImageView) findViewById(R.id.testID);
        Context context = test.getContext();
        GlideApp.with(context)

        RecyclerView topPicks = (RecyclerView) findViewById(R.id.top_picks);

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
