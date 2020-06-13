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
        public ImageView topPicksImage1;

        public ViewHolder(View itemView) {
            super(itemView);
            topPicksImage1 = (ImageView) itemView.findViewById(R.id.top_picks_image1);
        }
    }

    private List<Clothing> mClothes;
    private Context mContext;

    public TopPicksAdapter(Context context, List<Clothing> clothes){
        mClothes = clothes;
        mContext = context;
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
        Clothing clothing1 = mClothes.get(position);
        ImageView imageView1 = viewHolder.topPicksImage1;

        GlideApp.with(mContext)
                .load(clothing1.getImg())
                .centerCrop()
                .placeholder(R.drawable.loading)
                .into(imageView1);
    }

    @Override
    public int getItemCount(){
        return mClothes.size();
    }
}
