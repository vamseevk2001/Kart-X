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
    String img_url1 = "https://images-eu.ssl-images-amazon.com/images/G/31/img21/Laptops/Acer/Nitro-5/1242X450-re.jpg"; //Acer Nitro 5
    String img_url2 = "https://images-eu.ssl-images-amazon.com/images/G/31/img21/Laptops/February/D20687461_IN_PC_Laptops-Laptop-days--Apple-February_vday_770._CB660536128_SY500_.jpg"; //fire bolt wrist band
    String img_url3 = "https://images-eu.ssl-images-amazon.com/images/G/31/img21/Laptops/Microsoft/Surface-Days/Surface-Pro-X-1242x450._CB656766391_SY500_.jpg"; //sony
    String img_url4 = "https://images-eu.ssl-images-amazon.com/images/G/31/img20/Audio/Sony/NYC/Boat_Hero_1242x450.jpg"; //boat
    String img_url5 = "https://images-eu.ssl-images-amazon.com/images/G/31/img21/Monitors/Co-op/LG/ED_monitors_1242x450.jpg";//lg monitor


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ImageSlider imageSlider = (ImageSlider) findViewById(R.id.image_slider);

        List<SlideModel> imageList = new ArrayList<SlideModel>();
        imageList.add(new SlideModel(img_url1));
        imageList.add(new SlideModel(img_url2));
        imageList.add(new SlideModel(img_url3));
        imageList.add(new SlideModel(img_url4));
        imageList.add(new SlideModel(img_url5));

        imageSlider.setImageList(imageList, false);

    }


}