package com.example.mossa;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

//RecyclerAdapter adapted from https://guides.codepath.com/android/using-the-recyclerview
public class TopPicksAdapter extends RecyclerView.Adapter<TopPicksAdapter.ViewHolder> {

    //Local variable declaration
    private static ClickListener clickListener;
    private static List<Clothing> mClothes;
    private Context mContext;

    //Click listener adapted from https://antonioleiva.com/recyclerview-listener/
    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(ClickListener clickListener){
        TopPicksAdapter.clickListener = clickListener;
    }

    //View holder class is created
    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView topPicksImage1;
        public TextView topPicksText;

        //ViewHolder constructer creates the views based on whats in top_picks_layout.xml
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            topPicksText = (TextView) itemView.findViewById(R.id.top_picks_name);
            topPicksImage1 = (ImageView) itemView.findViewById(R.id.top_picks_image1);

        }

        @Override
        public void onClick(View v){
            clickListener.onItemClick(getAdapterPosition(), v);
        }

    }

    //Clothing objects to be put into itemView stored in mClothes array
    public TopPicksAdapter(Context context, List<Clothing> clothes){

        mClothes = clothes;
        mContext = context;
    }

    //View holder is inflated with the top_picks_layout.xml
    @Override
    public TopPicksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View topPickView = inflater.inflate(R.layout.top_picks_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(topPickView);
        return viewHolder;
    }


    //data is assigned to views within the view holder
    @Override
    public void onBindViewHolder(TopPicksAdapter.ViewHolder viewHolder, int position) {
        Clothing clothing1 = mClothes.get(position);
        ImageView imageView1 = viewHolder.topPicksImage1;
        TextView textView = viewHolder.topPicksText;

        GlideApp.with(mContext)
                .load(clothing1.getImg())
                .centerCrop()
                .placeholder(R.drawable.loading)
                .into(imageView1);
        textView.setText(clothing1.getName());
    }

    @Override
    public int getItemCount(){
        return mClothes.size();
    }

    public static Clothing getItem(int position){
        return mClothes.get(position);
    }
}
