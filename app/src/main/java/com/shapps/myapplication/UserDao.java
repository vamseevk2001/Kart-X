package com.shapps.myapplication;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.Map;

public class UserDao {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference userCollection = db.collection("users");

    void addUser(Users user){
        userCollection.document(user.getUid()).set(user);
    }

    void updateCart(String uid, ItemsDataClass cart){
        userCollection.document(uid).collection("kart").document(cart.getName()).set(cart);
    }

    void deleteItem(Context context, String uid, String name){
        userCollection.document(uid).collection("kart").document(name).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(context.getApplicationContext(), "Item deleted successfully....", Toast.LENGTH_SHORT).show();
                //total -= model.getPrice();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context.getApplicationContext(), "Sorry there was an error deleting your item ...", Toast.LENGTH_SHORT).show();
            }
        });
    }
    void cartTotal(ArrayList<String> items){
        int total = 0;
        for(int i = 0; i<items.size(); i++){
            items.get(i);
        }
    }

}
