package com.example.mossa;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.smarteist.autoimageslider.IndicatorView.PageIndicatorView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class DetailsActivity extends AppCompatActivity {
    SliderAdapter sliderAdapter;
    private SliderView svClothingPic;
    private TextView tvPriceVal;
    private TextView tvItemName;
    private TextView tvItemDesc;
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

        tvPriceVal = (TextView) findViewById(R.id.price_val);
        tvItemName = (TextView) findViewById(R.id.item_name);
        tvItemDesc = (TextView) findViewById(R.id.description_text);
        svClothingPic = (SliderView) findViewById(R.id.image_slider);
        tvViewCount = (TextView) findViewById(R.id.view_count);

        Intent intent = getIntent();
        clothing = (Clothing) intent.getSerializableExtra("Item");
        loadItem(clothing);
    }

    private void loadItem(Clothing clothing) {
        tvItemName.setText(clothing.getName());
        tvItemDesc.setText(clothing.getDesc());
        String priceString = Integer.toString(clothing.getPrice());
        tvPriceVal.setText("$" + priceString);
        String viewString = Integer.toString((clothing.getViewCount()));
        tvViewCount.setText("Views: " + viewString);

        sliderAdapter = new SliderAdapter(this, clothing);
        svClothingPic.setSliderAdapter(sliderAdapter);

        svClothingPic.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        svClothingPic.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        svClothingPic.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        svClothingPic.setIndicatorSelectedColor(getResources().getColor(R.color.colorSecondaryLight)); //Color.White
        svClothingPic.setIndicatorUnselectedColor(getResources().getColor(R.color.colorSecondaryDark)); //Color.Grey
        svClothingPic.setScrollTimeInSec(4); //set scroll delay in seconds :
        svClothingPic.startAutoCycle();


        LinearLayoutManager lm = new LinearLayoutManager(this);
    }

    public void homeButton(View view){

        Intent intentMain = new Intent(DetailsActivity.this, MainActivity.class);

        startActivity(intentMain);
    }
}