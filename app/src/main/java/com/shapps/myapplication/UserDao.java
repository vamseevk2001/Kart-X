package com.shapps.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class UserDao {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference userCollection = db.collection("users");
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseuser = mAuth.getCurrentUser();

    void addUser(Users user){
        userCollection.document(user.getUid()).set(user);
    }


    void addAddress(Address address){
        HashMap<String, Object> addr = new HashMap<>();
        addr.put("Address", address);
        userCollection.document(firebaseuser.getUid()).update(addr);
    }

    Boolean checkAddress(){
        final Boolean[] check = new Boolean[1];
        userCollection.document(firebaseuser.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.contains("Address"))
                    check[0] = true;
            }
        });
        return check[0];
    }


    void updateCart(String uid, ItemsDataClass cart){
        userCollection.document(uid).collection("kart").document(cart.getName()).set(cart);
    }

    void deleteItem(Context context, String uid, String name){
        userCollection.document(uid).collection("kart").document(name).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(context.getApplicationContext(), "Item deleted successfully....", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context.getApplicationContext(), "Sorry there was an error deleting your item ...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void cartTotal(Context con){

        //AtomicLong total = new AtomicLong();
        userCollection.document(firebaseuser.getUid()).collection("kart").get().addOnCompleteListener(task -> {
            //int total = 0;
            if (task.isSuccessful()) {
                for (DocumentSnapshot document : task.getResult()) {
                    int total = 0;
                    total += ((Long) document.get("price"));
                    Intent intent = new Intent(con.getApplicationContext(), cart.class);
                    intent.putExtra("totalPrice", total);
                }
            } else {
                Log.d(TAG, "Error getting documents: ", task.getException());
            }
        });
    }

}
