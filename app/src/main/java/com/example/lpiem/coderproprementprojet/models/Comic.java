package com.example.lpiem.coderproprementprojet.models;

import android.util.Log;

import java.util.ArrayList;

public class Comic {

    private int id;
    private String title;
    private int issueNumber;
    private String description;
    private String diamondCode;
    private String date;
    private double price;
    private int pageCount;
    private String image;
    private ArrayList<Creator> creators;

    public Comic(int id, String title, int issueNumber, String description, String diamondCode,
                 String date, double price, int pageCount, String image, ArrayList creators) {
        this.id = id;
        this.title = title;
        this.issueNumber = issueNumber;
        this.description = description;
        this.diamondCode = diamondCode;
        this.date = date;
        this.price = price;
        this.pageCount = pageCount;
        this.image = image;
        this.creators = creators;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public String getDescription() {
        return description;
    }

    public String getDiamondCode() {
        return diamondCode;
    }

    public String getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getImage() {
        Log.d("LOADPicture", "ICI Getter");
        return image;
    }

    public ArrayList<Creator> getCreators() {
        return creators;
    }
}
