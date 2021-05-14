package com.shapps.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

public class CartAdapter extends FirestoreRecyclerAdapter<ItemsDataClass, CartAdapter.ItemsViewHolder> {

    private Context mcon;
    public static int total = 0;

    class ItemsViewHolder extends RecyclerView.ViewHolder{

        TextView name, price;
        Button remove;
        ImageView image;
        RatingBar stars;
        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_cart);
            price = itemView.findViewById(R.id.price);
            remove = itemView.findViewById(R.id.remove);
            image = itemView.findViewById(R.id.img);
            stars = itemView.findViewById(R.id.cartStars);
        }
    }

    public CartAdapter(Context con, @NonNull FirestoreRecyclerOptions<ItemsDataClass> options) {
        super(options);
        mcon = con;
    }

    @Override
    protected void onBindViewHolder(@NonNull CartAdapter.ItemsViewHolder holder, int position, ItemsDataClass model) {
        holder.name.setText(model.getName());
        holder.stars.setRating(model.getStars());
        holder.price.setText("Rs. " + model.getPrice());
        Picasso.get().load(model.getImg_url()).into(holder.image);
        total += model.getPrice();
        Log.d("total", String.valueOf(total));
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                FirebaseUser firebaseuser = FirebaseAuth.getInstance().getCurrentUser();
                UserDao userDao = new UserDao();
                userDao.deleteItem(mcon.getApplicationContext(), firebaseuser.getUid(), model.getName());
            }
        });
    }

    @NonNull
    @Override
    public CartAdapter.ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartAdapter.ItemsViewHolder(view);
    }
}
