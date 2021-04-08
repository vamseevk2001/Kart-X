package com.shapps.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {
    TextView name,mail;
    Button logout;
    CircleImageView avatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        logout=findViewById(R.id.logout_id);
        name = findViewById(R.id.name);
        mail = findViewById(R.id.mail);
        avatar = findViewById(R.id.displayPicture);


        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
           if(signInAccount !=null){
               name.setText(signInAccount.getDisplayName());
               mail.setText(signInAccount.getEmail());
               Log.d("display Picture",signInAccount.getPhotoUrl().toString());
               Glide.with(getApplicationContext()).load(signInAccount.getPhotoUrl()).into(avatar);
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

    public void goBack(View view) {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
    }

    public void editAddresss1(View view) {
    }

    public void editAddresss2(View view) {
    }
}


//    fun display(view: View) {
//        val nickname: EditText = findViewById(R.id.edit_nickname)
//        val setName: TextView = findViewById(R.id.nickname)
//        setName.text = nickname.text
//        view.visibility = View.GONE
//        nickname.visibility = View.GONE
//        setName.visibility = View.VISIBLE
//        //Hide keyboard
//        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
//
//    }
//
//    fun update(view: View) {
//        val nickname: EditText = findViewById(R.id.edit_nickname)
//        val doneButton: Button = findViewById(R.id.done)
//        nickname.visibility = View.VISIBLE
//        doneButton.visibility = View.VISIBLE
//        view.visibility = View.GONE
//        nickname.requestFocus()
//        //show the keyboard
//        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.showSoftInput(nickname, 0)
//    }