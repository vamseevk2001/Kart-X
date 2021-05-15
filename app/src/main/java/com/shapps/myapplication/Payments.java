package com.shapps.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shreyaspatil.EasyUpiPayment.EasyUpiPayment;
import com.shreyaspatil.EasyUpiPayment.listener.PaymentStatusListener;
import com.shreyaspatil.EasyUpiPayment.model.TransactionDetails;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Payments extends AppCompatActivity {
    private EditText  upiEdt, nameEdt, descEdt;
    private TextView transactionDetailsTV,total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);

        upiEdt = findViewById(R.id.idEdtUpi);
        nameEdt = findViewById(R.id.idEdtName);
        descEdt = findViewById(R.id.idEdtDescription);
        Button makePaymentBtn = findViewById(R.id.idBtnMakePayment);
        transactionDetailsTV = findViewById(R.id.idTVTranscationDetails);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("ddMMyyyyHHmmss", Locale.getDefault());
        String transcId = df.format(c);

        makePaymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting data from our edit text.
                total = findViewById(R.id.total);
                total.setText("Rs " + CartAdapter.total);
                String upi = upiEdt.getText().toString();
                String name = nameEdt.getText().toString();
                String desc = descEdt.getText().toString();
                // on below line we are validating our text field.
                    // if the edit text is not empty then
                    // we are calling method to make payment.
                   // makePayment(total, upi, name, desc, transcId);

            }
        });
    }

    private void makePayment(String amount, String upi, String name, String desc, String transcationId) {
        // on below line we are calling an easy payment method and passing
        // all parameters to it such as upi id,name, description and others.
        final EasyUpiPayment easyUpiPayment = new EasyUpiPayment.Builder()
                .with(this)
                // on below line we are adding upi id.
                .setPayeeVpa(upi)
                // on below line we are setting name to which we are making oayment.
                .setPayeeName(name)
                // on below line we are passing transaction id.
                .setTransactionId(transcationId)
                // on below line we are passing transaction ref id.
                .setTransactionRefId(transcationId)
                // on below line we are adding description to payment.
                .setDescription(desc)
                // on below line we are passing amount which is being paid.
                .setAmount(amount)
                // on below line we are calling a build method to build this ui.
                .build();
        // on below line we are calling a start
        // payment method to start a payment.
        easyUpiPayment.startPayment();
        // on below line we are calling a set payment
        // status listener method to call other payment methods.
        easyUpiPayment.setPaymentStatusListener((PaymentStatusListener) this);
    }
    public void onTransactionCompleted(TransactionDetails transactionDetails) {
        // on below line we are getting details about transaction when completed.
        String transcDetails = transactionDetails.getStatus().toString() + "\n" + "Transaction ID : " + transactionDetails.getTransactionId();
        transactionDetailsTV.setVisibility(View.VISIBLE);
        // on below line we are setting details to our text view.
        transactionDetailsTV.setText(transcDetails);
    }

    }



