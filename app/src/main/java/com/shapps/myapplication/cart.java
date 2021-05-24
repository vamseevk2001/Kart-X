package com.shapps.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import static android.content.ContentValues.TAG;

public class cart extends AppCompatActivity {

    private RecyclerView recyclerView;
    CartAdapter adapter;
    Query query;
    TextView totalView;
    ConstraintLayout empty;
    Button pay;
    long totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setRecyclerView();

        UserDao cart = new UserDao();
        cart.cartTotal(this);
        setTotal();
        empty = findViewById(R.id.emptyCart);
        pay = findViewById(R.id.pay_button);
        totalView = findViewById(R.id.total);

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
    }

    long total(){
        return totalPrice;
    }

    public void setTotal(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference userCollection = db.collection("users");
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseuser = mAuth.getCurrentUser();
        userCollection.document(firebaseuser.getUid()).collection("kart").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable  QuerySnapshot value,  FirebaseFirestoreException error) {
                long total = 0;
                    for (DocumentSnapshot document : value.getDocuments()) {
                        total += ((Long) document.get("price"));
                }
                totalView = findViewById(R.id.total);
                totalView.setText("Total amount : Rs " + total);
                totalPrice = total;
                if(total == 0){
                    empty.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    pay.setVisibility(View.GONE);
                    totalView.setVisibility(View.GONE);
                }
            }
        });
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

    public void back(View view) {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
        finish();
    }

    public void dashboard(View view) {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
        finish();
    }

    public void proceedPay(View view) {
        Intent intent = new Intent(this, Payments.class);
        intent.putExtra("total", totalPrice);
        startActivity(intent);
    }
}