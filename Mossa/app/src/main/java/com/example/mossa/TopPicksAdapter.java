package com.example.mossa;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.os.Bundle;

import com.bumptech.glide.Glide;

import java.util.List;


public class TopPicksAdapter extends RecyclerView.Adapter<TopPicksAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView topPicksImage;

        public ViewHolder(View itemView) {
            super(itemView);

            topPicksImage = (ImageView) itemView.findViewById(R.id.top_picks_image);
        }
    }

    private List<Clothing> mClothes;

    public TopPicksAdapter(List<Clothing> clothes){
        mClothes = clothes;
    }

    @Override
    public TopPicksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View topPickView = inflater.inflate(R.layout.top_picks_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(topPickView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TopPicksAdapter.ViewHolder viewHolder, int position) {
        Clothing clothing = mClothes.get(position);

        Context context = viewHolder.topPicksImage.getContext();
        GlideApp.with(context)
                .load(clothing.getImg())
                .into(viewHolder.topPicksImage);

    }

    @Override
    public int getItemCount(){
        return mClothes.size();
    }
}
