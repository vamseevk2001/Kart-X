package com.shapps.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class cart extends AppCompatActivity {

    private RecyclerView recyclerView;
    CartAdapter adapter;
    Query query;
    TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setRecyclerView();

    }

    public void setRecyclerView(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser firebaseuser = FirebaseAuth.getInstance().getCurrentUser();
        query = db.collection("users").document(firebaseuser.getUid()).collection("kart");
        FirestoreRecyclerOptions<ItemsDataClass> response = new FirestoreRecyclerOptions.Builder<ItemsDataClass>().setQuery(query, ItemsDataClass.class).build();
        recyclerView = findViewById(R.id.orders);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CartAdapter(cart.this, response);
        recyclerView.setAdapter(adapter);
        total = findViewById(R.id.total);
        total.setText("Rs " + CartAdapter.total);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }

    public void proceedPay(View view) {
        Intent intent = new Intent(this,Payments.class);
        startActivity(intent);
        finish();
    }
}