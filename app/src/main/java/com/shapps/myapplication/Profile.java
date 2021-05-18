package com.shapps.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
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
        TextInputEditText address1 = findViewById(R.id.address1_input);
        Button doneAddress1 = findViewById(R.id.doneAddress1);
        TextView setAddress1 = findViewById(R.id.address1);

        //address1.setText(setAddress1.getText());
        address1.setVisibility(View.VISIBLE);
        doneAddress1.setVisibility(View.VISIBLE);
        setAddress1.setVisibility(View.GONE);
        address1.requestFocus();

        //showing the keyboard to enter address 1 :
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(address1, 0);
    }

    public void editAddresss2(View view) {
        TextInputEditText address2 = findViewById(R.id.address2_input);
        Button doneAddress2 = findViewById(R.id.doneAddress2);
        TextView setAddress2 = findViewById(R.id.address2);

        //address1.setText(setAddress1.getText());
        address2.setVisibility(View.VISIBLE);
        doneAddress2.setVisibility(View.VISIBLE);
        setAddress2.setVisibility(View.GONE);
        address2.requestFocus();

        //showing the keyboard to enter address 1 :
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(address2, 0);
    }

    public void editPhone(View view) {
        TextInputEditText phoneNo = findViewById(R.id.phoneNoInput);
        Button donePhone = findViewById(R.id.donePhone);
        TextView setPhone = findViewById(R.id.phoneNo);

        //address1.setText(setAddress1.getText());
        phoneNo.setVisibility(View.VISIBLE);
        donePhone.setVisibility(View.VISIBLE);
        setPhone.setVisibility(View.GONE);
        phoneNo.requestFocus();

        //showing the keyboard to enter address 1 :
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(phoneNo, 0);
    }
    public void setPhone(View view) {
        TextInputEditText phoneNo = findViewById(R.id.phoneNoInput);
        TextView setPhone = findViewById(R.id.phoneNo);
        setPhone.setText(phoneNo.getText());
        view.setVisibility(View.GONE);
        phoneNo.setVisibility(View.GONE);
        setPhone.setVisibility(View.VISIBLE);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void setAddress1(View view) {
        TextInputEditText address1 = findViewById(R.id.address1_input);
        TextView setAddress1 = findViewById(R.id.address1);
        UserDao user = new UserDao();
        user.addAddress(new Users(address1.getText().toString()));
        user.getAddress(setAddress1);
        view.setVisibility(View.GONE);
        address1.setVisibility(View.GONE);
        setAddress1.setVisibility(View.VISIBLE);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void setAddress2(View view) {
        TextInputEditText address2 = findViewById(R.id.address2_input);
        TextView setAddress2 = findViewById(R.id.address2);
        setAddress2.setText(address2.getText());
        view.setVisibility(View.GONE);
        address2.setVisibility(View.GONE);
        setAddress2.setVisibility(View.VISIBLE);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
