package com.shapps.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import com.shapps.myapplication.cart.*;

import static com.shapps.myapplication.R.color.lightGrey;

public class Payments extends AppCompatActivity implements PaymentResultListener {
    Button pay;
    long totalPrice;
    CardView address;
    LinearLayout addAddress;
    HashMap addr;
    TextView name, phone, houseNo, area, city, pin, state;

    @Override
    protected void onResume() {
        super.onResume();
        setAddress();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);

        pay = findViewById(R.id.pay);
        address = findViewById(R.id.address);
        addAddress = findViewById(R.id.addAddress);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        houseNo = findViewById(R.id.houseNo);
        area = findViewById(R.id.area);
        city = findViewById(R.id.city);
        pin = findViewById(R.id.pinCode);
        state = findViewById(R.id.state);

        setAddress();

        totalPrice = getIntent().getLongExtra("total", 0);
        DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
        pay.setText("PAY ₹ " + formatter.format((double) totalPrice));

    }

    void setAddress(){
        UserDao user = new UserDao();
        user.userCollection.document(user.firebaseuser.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.contains("Address")){
                    address.setVisibility(View.VISIBLE);
                    addAddress.setVisibility(View.GONE);
                    addr = (HashMap) documentSnapshot.get("Address");
                    name.setText(addr.get("name").toString());
                    houseNo.setText(addr.get("houseNo").toString());
                    area.setText(addr.get("area").toString());
                    pay.setClickable(true);
                    city.setText(addr.get("city").toString() + ", " + addr.get("state").toString() + ", " +addr.get("pin").toString());
                    phone.setText("+91 " + addr.get("phone").toString());
                }
                else {
                    address.setVisibility(View.GONE);
                    addAddress.setVisibility(View.VISIBLE);
                    pay.setBackgroundColor(getResources().getColor(lightGrey));
                    pay.setClickable(false);
                }
            }
        });
    }

    public void Gpay(View view) {
        double amt = (double) totalPrice;
        startPayment(amt);
    }

    private void startPayment(double amount) {
        Activity activity =this;
        Checkout checkout =new Checkout();
        JSONObject object =new JSONObject();
        try{
            object.put("name","Sharvesh");
            object.put("description","Payment From Cart");
            amount *= 100;
            object.put("amount",amount);
            object.put("currency","INR");
            object.put("prefill.contact","7904592032");
            object.put("prefill.email","sharveshsreethar0806@gmail.com");
            checkout.open(activity,object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this,"success",Toast.LENGTH_SHORT).show();
        UserDao cart = new UserDao();
        cart.deleteCart();
        Intent intent = new Intent(this, OrderPlaced.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this,"Failure",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, cart.class);
        startActivity(intent);
        finish();
    }

    public void addNewAddress(View view) {
        Intent intent = new Intent(this, AddressInput.class);
        startActivity(intent);
    }

    public void editAddress(View view) {
        Intent intent = new Intent(this, AddressInput.class);
        intent.putExtra("address", addr);
        startActivity(intent);
    }
}