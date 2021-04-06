package com.shapps.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Dashboard extends AppCompatActivity {

    //item images
    String img_url1 = "https://images-eu.ssl-images-amazon.com/images/G/31/img21/Laptops/Acer/Nitro-5/1242X450-re.jpg"; //Acer Nitro 5
    String img_url2 = "https://images-eu.ssl-images-amazon.com/images/G/31/img21/Laptops/February/D20687461_IN_PC_Laptops-Laptop-days--Apple-February_vday_770._CB660536128_SY500_.jpg"; //fire bolt wrist band
    String img_url3 = "https://images-eu.ssl-images-amazon.com/images/G/31/img21/Laptops/Microsoft/Surface-Days/Surface-Pro-X-1242x450._CB656766391_SY500_.jpg"; //sony
    String img_url4 = "https://images-eu.ssl-images-amazon.com/images/G/31/img20/Audio/Sony/NYC/Boat_Hero_1242x450.jpg"; //boat
    String img_url5 = "https://images-eu.ssl-images-amazon.com/images/G/31/img21/Monitors/Co-op/LG/ED_monitors_1242x450.jpg";//lg monitor
    Intent intent = getIntent();

    private RecyclerView recyclerView;
    ProductsAdapter adapter;
    DatabaseReference mbase;
    CircleImageView avatar;

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


        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        avatar = findViewById(R.id.avatar);

        Glide.with(getApplicationContext()).load(signInAccount.getPhotoUrl()).into(avatar);

        setRecyclerView();

    }

    public void setRecyclerView(){
        mbase = FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById(R.id.products);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        FirebaseRecyclerOptions<ItemsDataClass> options = new FirebaseRecyclerOptions.Builder<ItemsDataClass>().setQuery(mbase, ItemsDataClass.class).build();
        adapter = new ProductsAdapter(options);
        recyclerView.setAdapter(adapter);


    }
    @Override
    protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }


    public void loadProfile(View view) {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }
}