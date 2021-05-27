package com.shapps.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.squareup.picasso.Picasso;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {
    TextView name, mail, phone, address, phoneLable, addressLable;
    Button logout, editAddress;
    CircleImageView avatar;
    HashMap addr;
    private FirebaseAuth mAuth;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onResume() {
        super.onResume();
        setDetails();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        logout = findViewById(R.id.logout_id);
        name = findViewById(R.id.name);
        mail = findViewById(R.id.mail);
        avatar = findViewById(R.id.displayPicture);
        phone = findViewById(R.id.phoneNo);
        address = findViewById(R.id.addressProfile);
        editAddress = findViewById(R.id.editAddress);
        phoneLable = findViewById(R.id.phoneLabel);
        addressLable = findViewById(R.id.addressLabel);
        setDetails();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (signInAccount != null) {
            name.setText(signInAccount.getDisplayName());
            mail.setText(signInAccount.getEmail());
            Log.d("display Picture", signInAccount.getPhotoUrl().toString());
            Glide.with(getApplicationContext()).load(signInAccount.getPhotoUrl()).into(avatar);
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();
                mGoogleSignInClient.signOut();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        editAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddressInput.class);
                intent.putExtra("address", addr);
                startActivity(intent);
            }
        });


    }

    public void goBack(View view) {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
    }

    void setDetails() {
        UserDao details = new UserDao();
        details.userCollection.document(details.firebaseuser.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.contains("Address")) {
                    addr = (HashMap) documentSnapshot.get("Address");
                    address.setVisibility(View.VISIBLE);
                    phone.setVisibility(View.VISIBLE);
                    phoneLable.setVisibility(View.VISIBLE);
                    addressLable.setVisibility(View.VISIBLE);
                    editAddress.setText("Edit Details");
                    address.setText(MessageFormat.format("{0}\n{1}\n{2}\n{3}\n{4}", addr.get("houseNo").toString().trim(),
                            addr.get("area").toString().trim(), addr.get("city").toString().trim(),
                            addr.get("state").toString().trim(), addr.get("pin").toString().trim()));
                    phone.setText("+91 " + addr.get("phone").toString());
                } else {
                    address.setVisibility(View.GONE);
                    phone.setVisibility(View.GONE);
                    phoneLable.setVisibility(View.GONE);
                    addressLable.setVisibility(View.GONE);
                    editAddress.setText("Add Address");
                    editAddress.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), AddressInput.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });
    }


}
