package com.shapps.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class AddressInput extends AppCompatActivity {
    TextInputEditText name, phone, houseNo, area, city, pin, state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_input);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        houseNo = findViewById(R.id.houseNo);
        area = findViewById(R.id.area);
        city = findViewById(R.id.city);
        pin = findViewById(R.id.pinCode);
        state = findViewById(R.id.state);
    }

    public void addAddress(View view) {
        if(name.getText().length() != 0 &&
                phone.getText().length() != 0 &&
                houseNo.getText().length() != 0 &&
                area.getText().length() != 0 &&
                city.getText().length() != 0 &&
                pin.getText().length() != 0 &&
                state.getText().length() != 0 )
        {
            Address address = new Address(name.getText().toString(),
                    phone.getText().toString(), pin.getText().toString(),
                    houseNo.getText().toString(), area.getText().toString(),
                    city.getText().toString(), state.getText().toString());
            UserDao userDao = new UserDao();
            userDao.addAddress(address);
            Intent intent = new Intent(this, cart.class);
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(this, "Please fill all the feilds..", Toast.LENGTH_SHORT).show();
        }
    }
}