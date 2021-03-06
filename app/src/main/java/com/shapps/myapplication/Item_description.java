package com.shapps.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Item_description extends AppCompatActivity {
    ImageView product_img;
    TextView product_name, product_desc, product_price;
    RatingBar product_rating;
    Button addToKart;
    DatabaseReference ref;
    LottieAnimationView loading;
    String itemKey, name, imgUrl, description;
    long price, stars;
    private FirebaseAuth mAuth;
    public ArrayList<String> cartItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_description);

        product_img = findViewById(R.id.desc_prod_image);
        product_name = findViewById(R.id.prod_name);
        product_desc = findViewById(R.id.productDescription);
        product_price = findViewById(R.id.productPrice);
        product_rating = findViewById(R.id.productRating);
        addToKart = findViewById(R.id.addToKart);
        //loading = findViewById(R.id.loading);

        itemKey = getIntent().getStringExtra("key");
        Log.println(Log.ASSERT, "item", itemKey);
        ref = FirebaseDatabase.getInstance().getReference().child(itemKey);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    name = snapshot.child("name").getValue().toString();
                    imgUrl = snapshot.child("img_url").getValue().toString();
                    description = snapshot.child("description").getValue().toString();
                    stars = (long) snapshot.child("stars").getValue();
                    price = (long) snapshot.child("price").getValue();
                    Log.d("STARS", name);
                    product_name.setText(name);
                    product_desc.setText(description);
                    DecimalFormat formatter = new DecimalFormat("###,###,##0");
                    product_price.setText(String.format("Rs %s", formatter.format((double) price)));
                    product_rating.setRating(stars);
                    Glide.with(getApplicationContext()).load(imgUrl).into(product_img);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "DB error", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void goBack(View view) {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
        finish();
    }

    public void addToKart(View view) {
        mAuth= FirebaseAuth.getInstance();
        FirebaseUser firebaseuser = mAuth.getCurrentUser();
        UserDao userDao = new UserDao();
        ItemsDataClass cart = new ItemsDataClass(name, imgUrl, (int) price, description, (int) stars);
        userDao.updateCart(firebaseuser.getUid(), cart);
        cartItems.add(name);
        view.setVisibility(View.GONE);
        Button gotocart = findViewById(R.id.gotoKart);
        gotocart.setVisibility(View.VISIBLE);
    }

    public void goToKart(View view) {
        Intent intent = new Intent(this, cart.class);
        startActivity(intent);
        finish();
    }
}