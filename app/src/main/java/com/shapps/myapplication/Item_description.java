package com.shapps.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Item_description extends AppCompatActivity {

    ImageView product_img;
    TextView product_name, product_desc, product_price;
    RatingBar product_rating;
    DatabaseReference ref;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_description);

        product_img = findViewById(R.id.desc_prod_image);
        product_name = findViewById(R.id.prod_name);
        product_desc = findViewById(R.id.productDescription);
        product_price = findViewById(R.id.productPrice);
        product_rating = findViewById(R.id.productRating);

        String itemKey = getIntent().getStringExtra("key");
        Log.println(Log.ASSERT, "item", itemKey);
        ref = FirebaseDatabase.getInstance().getReference().child(itemKey);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String name = snapshot.child("name").getValue().toString();
                    String imgUrl = snapshot.child("img_url").getValue().toString();
                    String description = snapshot.child("description").getValue().toString();
                    long stars = (long) snapshot.child("stars").getValue();
                    long price = (long) snapshot.child("price").getValue();

                    Log.d("STARS", name);

                    product_name.setText(name);
                    product_desc.setText(description);
                    product_price.setText(String.format("Rs %d", price));
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
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference userCollection = db.collection("users");
        mAuth= FirebaseAuth.getInstance();
        FirebaseUser firebaseuser = mAuth.getCurrentUser();

       // userCollection.document(firebaseuser.getUid())
    }

    public void goToKart(View view) {
        Intent intent = new Intent(this, cart.class);
        startActivity(intent);
    }
}