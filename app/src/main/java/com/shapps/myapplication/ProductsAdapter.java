package com.shapps.myapplication;

import android.content.Context;
import android.content.Intent;
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

public class ProductsAdapter extends FirebaseRecyclerAdapter<ItemsDataClass, ProductsAdapter.ItemsViewHolder> {

    private Context mContext;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ProductsAdapter(@NonNull FirebaseRecyclerOptions<ItemsDataClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ItemsViewHolder holder, int position, @NonNull ItemsDataClass model) {
        holder.productName.setText(model.getName());
        holder.stars.setRating(model.getStars());
        holder.price.setText("Rs. " + model.getPrice());
        Picasso.get().load(model.getImg_url()).into(holder.productImage);

    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_products, parent, false);
        return new ProductsAdapter.ItemsViewHolder(view);
    }


    class ItemsViewHolder extends RecyclerView.ViewHolder{

        TextView productName, price;
        ImageView productImage;
        RatingBar stars;
        CardView card;


        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productImage = itemView.findViewById(R.id.product_image);
            price = itemView.findViewById(R.id.productPrice);
            stars = itemView.findViewById(R.id.productRating);
            card = itemView.findViewById(R.id.item_card);
        }
    }


}

interface onItemClick {

}

