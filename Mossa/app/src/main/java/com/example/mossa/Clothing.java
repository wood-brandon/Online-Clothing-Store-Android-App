package com.example.mossa;

import android.net.Uri;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Clothing {
    private String Name;
    private String Category;
    private int Price;
    private String ImgAddr;

    public Clothing(String Name,  String Category, int Price, String ImgAddr){
        this.Name = Name;
        this.Category = Category;
        this.Price = Price;
        this.ImgAddr = ImgAddr;
    }

    public String getName(){
        return Name;
    }
    public String getImg(){
        return ImgAddr;
    }
    public String getCategory(){
        return Category;
    }
    public int getPrice(){
        return Price;
    }


    // class creates clothing catalogue where we get all our data
    public static ArrayList<Clothing> createClothingCatalogue(){
        //define reader
        BufferedReader reader;
        try {
            //create new reader and read clothing_catalog.txt file
            File file1 = new File("clothing_catalog.txt");
            System.out.println(file1.getAbsolutePath());
            reader = new BufferedReader(new FileReader("catalogue/clothing_catalog.txt"));
            //create array of clothing objects
            ArrayList<Clothing> clothingCatalogue = new ArrayList<Clothing>();
            //Read first set of data for first clothing object
            String name = reader.readLine();
            String category = reader.readLine();
            int price = Integer.parseInt(reader.readLine());
            String ImgAddr = reader.readLine();

            //loop through entire .txt file
            while (name != null) {
                //add clothing object to catalogue array
                clothingCatalogue.add(new Clothing(name, category, price, ImgAddr));

                name = reader.readLine();
                category = reader.readLine();
                price = Integer.parseInt(reader.readLine());
                ImgAddr = reader.readLine();

            }
            reader.close();
            return clothingCatalogue;
        }catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
