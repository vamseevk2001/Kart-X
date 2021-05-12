package com.shapps.myapplication;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserDao {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference userCollection = db.collection("users");

    void addUser(Users user){
        userCollection.document(user.getUid()).set(user);
    }

}
