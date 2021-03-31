package com.shapps.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {

    //item images
    String img_url1 = "https://images-na.ssl-images-amazon.com/images/I/61WjZrbnqML._SX466_.jpg"; //boat
    String img_url2 = "https://images-na.ssl-images-amazon.com/images/I/818c8Lnb8GL._SL1500_.jpg"; //skull candy
    String img_url3 = "https://images-na.ssl-images-amazon.com/images/I/81v7oPP75kL._SL1500_.jpg"; //sony
    String img_url4 = "https://images-na.ssl-images-amazon.com/images/I/71b0AbTo9RL._SL1500_.jpg"; //boat
    String img_url5 = "https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/airpods-max-select-skyblue-202011?wid=470&hei=556&fmt=png-alpha&.v=1604022365000";//apple


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ImageSlider imageSlider = (ImageSlider) findViewById(R.id.image_slider);

        List<SlideModel> imageList = new ArrayList<SlideModel>();
        imageList.add(new SlideModel(img_url1, "Boat"));
        imageList.add(new SlideModel(img_url2, "Skull Candy"));
        imageList.add(new SlideModel(img_url3, "Sony"));
        imageList.add(new SlideModel(img_url4, "Boat"));
        imageList.add(new SlideModel(img_url5, "Apple ear pods pro"));


        imageSlider.setImageList(imageList, true);

    }


}