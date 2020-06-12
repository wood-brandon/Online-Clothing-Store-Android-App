package com.example.mossa;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailsActivity extends AppCompatActivity {
    private ImageView ivClothingPic;
    private TextView tvPriceVal;
    private TextView tvItemName;
    private TextView tvViewCount;
    Clothing clothing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //setting parameters for custom action bar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_nosearch);
        //set the colour of the action bar
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#a98274"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        ivClothingPic = (ImageView) findViewById(R.id.image_slider);
        tvPriceVal = (TextView) findViewById(R.id.price_val);
        tvItemName = (TextView) findViewById(R.id.item_name);
        tvViewCount = (TextView) findViewById(R.id.view_count); // DEBUGGING ONLY
        Intent intent = getIntent();
        
        clothing = (Clothing) intent.getSerializableExtra("Item");
        loadItem(clothing);

    }

    private void loadItem(Clothing clothing) {
        String priceString = Integer.toString(clothing.getPrice());
        tvPriceVal.setText("$" + priceString);
        tvItemName.setText(clothing.getName());
        String viewString = Integer.toString((clothing.getViewCount()));
        tvViewCount.setText(viewString);

        GlideApp.with(this)
                .load(clothing.getImg())
                .centerCrop()
                .placeholder(R.drawable.testimage)
                .into(ivClothingPic);
    }
}