package com.shapps.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class Payments extends AppCompatActivity implements PaymentResultListener {
private TextView amount;
private Button pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);
        amount=findViewById(R.id.amt);
        pay=findViewById(R.id.pay);
        String amt = getIntent().getStringExtra("total");
        amount.setText(amt);
    }

    public void Gpay(View view) {
        String amt = (String) amount.getText();
        if(amt.isEmpty()){
            Toast.makeText(this,"Enter Amount",Toast.LENGTH_SHORT).show();
        }

        else{
            startPayment(amt);
        }
    }

    private void startPayment(String amount) {
        Activity activity =this;
        Checkout checkout =new Checkout();
        JSONObject object =new JSONObject();
        try{
            object.put("name","Sharvesh");
            object.put("description","Payment From Cart");
            double amo=Double.parseDouble(amount);
            amo=amo*100;
            object.put("amount",amo);
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
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this,"Failure",Toast.LENGTH_SHORT).show();    }
}