package com.example.mossa;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ImageView;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Clothing> {

    public Context mContext;

    private static class ViewHolder{
        public ImageView ivClothingImg;
        public TextView tvName;
        public TextView tvPrice;
    }


    public ListAdapter(Context context, ArrayList<Clothing> aClothes){
        super(context, 0,  aClothes);
        mContext = context;
    }

    //Translates a particular item given a position into a relevant row within AdapterView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get data for a specific pos
        final Clothing clothing = getItem(position);
        //check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder;

        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_clothing,parent,false);

            viewHolder.ivClothingImg = (ImageView)convertView.findViewById(R.id.ivClothingImg);
            viewHolder.tvName = (TextView)convertView.findViewById(R.id.tvName);
            viewHolder.tvPrice = (TextView)convertView.findViewById(R.id.tvPrice);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //Populate the data into the template view using the data object
        viewHolder.tvName.setText(clothing.getName());
        String priceString = Integer.toString(clothing.getPrice());
        viewHolder.tvPrice.setText("$" + priceString);
        //int resID = clothing.getImg();
        GlideApp.with(mContext)
                .load(clothing.getImg())
                .centerCrop()
                .placeholder(R.drawable.testimage)
                .into(viewHolder.ivClothingImg);

        return convertView;
    }

}
