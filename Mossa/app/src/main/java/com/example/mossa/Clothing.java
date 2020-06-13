package com.example.mossa;

import android.content.Context;
import android.net.Uri;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;

public class Clothing implements Serializable {
    private String Name;
    private String Category;
    private int Price;
    private String ImgAddr;
    private String ImgAddr2;
    private String ImgAddr3;
    private String desc;
    private int ViewCount;

    public Clothing(String Name,  String Category, int Price, String ImgAddr, String ImgAddr2, String ImgAddr3, String desc){
        this.Name = Name;
        this.Category = Category;
        this.Price = Price;
        this.ImgAddr = ImgAddr;
        this.ImgAddr2 = ImgAddr2;
        this.ImgAddr3 = ImgAddr3;
        this.desc = desc;
        this.ViewCount = 0;
    }

    public String getName(){
        return Name;
    }
    public String getDesc(){ return desc; }
    public String getImg(){
        return ImgAddr;
    }
    public String getImg2(){ return ImgAddr2; }
    public String getImg3(){ return ImgAddr3; }
    public String getCategory(){
        return Category;
    }
    public int getPrice(){
        return Price;
    }
    public int getViewCount() { return ViewCount; }
    public void addView() { ViewCount += 1 ; }


    // class creates clothing catalogue where we get all our data
    public static ArrayList<Clothing> createClothingCatalogue(Context context){
        //create array of clothing objects
        ArrayList<Clothing> clothingCatalogue = new ArrayList<>();
        //define reader
        BufferedReader reader;


        try {
            //create new reader and read clothing_catalog.txt file
            final InputStream file = context.getAssets().open("clothing_catalogue.txt");
            reader = new BufferedReader(new InputStreamReader(file));

            //Read first set of data for first clothing object
            String name = reader.readLine();
            String category;
            int price;
            String ImgAddr;
            String ImgAddr2;
            String ImgAddr3;
            String desc;

            //loop through entire .txt file
            while (name != null) {
                category = reader.readLine();
                price = Integer.parseInt(reader.readLine());
                ImgAddr = reader.readLine();
                ImgAddr2 = reader.readLine();
                ImgAddr3 = reader.readLine();
                desc = reader.readLine();
                //add clothing object to catalogue array
                clothingCatalogue.add(new Clothing(name, category, price, ImgAddr, ImgAddr2, ImgAddr3, desc));
                name = reader.readLine();
            }
            reader.close();
            return clothingCatalogue;
        }catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
