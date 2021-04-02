package com.shapps.myapplication;

public class ItemsDataClass {
    private String name;
    private String img_url;
    private int price;
    private String description;
    private int stars;

    public String getName(){
        return this.name;
    }

    public int getStars(){
        return this.stars;
    }

    public String getImg_url(){
        return this.img_url;
    }

    public String getDescription(){
        return this.description;
    }

    public int getPrice(){
        return this.price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg_url(String img_url){
        this.img_url = img_url;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setStars(int stars){
        this.stars = stars;
    }

    public ItemsDataClass(){}

    public ItemsDataClass(String name, String img_url, int price, String description) {
        this.name = name;
        this.img_url = img_url;
        this.price = price;
        this.description = description;
    }

}
