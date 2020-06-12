package com.example.mossa;

import androidx.appcompat.app.AppCompatActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ivClothingPic = (ImageView) findViewById(R.id.image_slider);
        tvPriceVal = (TextView) findViewById(R.id.price_val);
        tvItemName = (TextView) findViewById(R.id.item_name);

    }
}