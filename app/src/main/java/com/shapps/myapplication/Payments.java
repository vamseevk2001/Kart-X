package com.shapps.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import org.json.JSONException;
import org.json.JSONObject;

public class Payments extends AppCompatActivity implements PaymentResultListener {
private TextView amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);
        amount=findViewById(R.id.amt);
        long amt = getIntent().getLongExtra("total", 0);
        amount.setText(String.valueOf(amt));
    }

    public void Gpay(View view) {
        String amt = (String) amount.getText();
            startPayment(amt);

    }

    private void startPayment(String amount) {
        Activity activity =this;
        Checkout checkout =new Checkout();
        JSONObject object =new JSONObject();
        try{
            object.put("name","Sharvesh");
            object.put("description","Payment From Cart");
            double amo=Double.parseDouble(amount);
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