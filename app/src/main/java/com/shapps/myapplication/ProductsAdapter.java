package com.shapps.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ProductsAdapter extends FirebaseRecyclerAdapter<ItemsDataClass, ProductsAdapter.ItemsViewHolder> {

    private Context mcon;
    public ProductsAdapter(Context con, @NonNull FirebaseRecyclerOptions<ItemsDataClass> options) {
        super(options);
        mcon = con;
    }

    @Override
    protected void onBindViewHolder(@NonNull ItemsViewHolder holder, int position, @NonNull ItemsDataClass model) {
        holder.productName.setText(model.getName());
        holder.stars.setRating(model.getStars());
        DecimalFormat formatter = new DecimalFormat("###,###,##0");
        String productPrice = formatter.format((double) model.getPrice());
        holder.price.setText("Rs "+ productPrice);
        Picasso.get().load(model.getImg_url()).into(holder.productImage);
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcon, Item_description.class);
                intent.putExtra("key", getRef(position).getKey());
                Log.println(Log.ASSERT, "item", getRef(position).getKey());
                mcon.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_products, parent, false);
        return new ProductsAdapter.ItemsViewHolder(view);
    }


    class ItemsViewHolder extends RecyclerView.ViewHolder {

        TextView productName, price;
        ImageView productImage;
        RatingBar stars;
        CardView card;
        View v;


        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productImage = itemView.findViewById(R.id.product_image);
            price = itemView.findViewById(R.id.productPrice);
            stars = itemView.findViewById(R.id.productRating);
            card = itemView.findViewById(R.id.item_card);
            v = itemView;
        }
    }


}




