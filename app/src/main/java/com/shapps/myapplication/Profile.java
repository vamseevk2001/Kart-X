package com.shapps.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity {
    TextView name,mail;
    Button logout;
    ShapeableImageView avatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        logout=findViewById(R.id.logout_id);
        name = findViewById(R.id.name);
        mail = findViewById(R.id.mail);


        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
           if(signInAccount !=null){
               name.setText(signInAccount.getDisplayName());
               mail.setText(signInAccount.getEmail());
               Log.d("display Picture",signInAccount.getPhotoUrl().toString());
               Intent intent = new Intent(this, Dashboard.class);
               intent.putExtra("displayImage", signInAccount.getPhotoUrl().toString());
           }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}