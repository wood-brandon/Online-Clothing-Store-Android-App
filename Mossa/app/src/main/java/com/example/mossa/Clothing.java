package com.example.mossa;

import android.net.Uri;

public class Clothing {
    private String Name;
    private String Category;
    private int Price;
    private Uri ImgAddr;

    public Clothing(String Name,  String Category, int Price, Uri ImgAddr){
        this.Name = Name;
        this.Category = Category;
        this.Price = Price;
        this.ImgAddr = ImgAddr;
    }

    public String getName(){
        return Name;
    }
    public Uri getImg(){
        return ImgAddr;
    }
    public String getCategory(){
        return Category;
    }
    public int getPrice(){
        return Price;
    }

}
