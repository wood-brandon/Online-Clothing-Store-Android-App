package com.example.mossa;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;
import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends
        SliderViewAdapter<SliderAdapter.myViewHolder> {

    private Context context;
    Clothing clothing;
    private List<String> mSliderImgs = new ArrayList<>();

    public SliderAdapter(Context context,  Clothing clothing) {
        this.context = context;
        this.clothing = clothing;
        this.mSliderImgs.add(clothing.getImg());
        this.mSliderImgs.add(clothing.getImg2());
        this.mSliderImgs.add(clothing.getImg3());
    }

    public class myViewHolder extends SliderViewAdapter.ViewHolder {
        View itemView;
        ImageView imageViewBackground;

        public myViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            this.itemView = itemView;
        }
    }

    @Override
    public SliderAdapter.myViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_slider_item, null);
        return new myViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(myViewHolder viewHolder, final int position) {
        String sliderImage = mSliderImgs.get(position);
        Glide.with(viewHolder.itemView)
                .load(sliderImage)
                .fitCenter()
                .into(viewHolder.imageViewBackground);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,clothing.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mSliderImgs.size();
    }



}