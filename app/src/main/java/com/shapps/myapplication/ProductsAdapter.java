package com.shapps.myapplication;

import android.content.Context;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductsAdapter extends FirebaseRecyclerAdapter<ItemsDataClass, ProductsAdapter.ItemsViewHolder> {

    private Context mcon;
   // private onItemClickListener mListener;


//    public interface onItemClickListener {
//        void onItemClick(FirebaseRecyclerOptions<ItemsDataClass> options, int position);
//    }
//
//    public void setOnItemClickListener(onItemClickListener listener){
//        mListener = listener;
//    }

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ProductsAdapter(Context con, @NonNull FirebaseRecyclerOptions<ItemsDataClass> options) {
        super(options);
        mcon = con;
    }

    @Override
    protected void onBindViewHolder(@NonNull ItemsViewHolder holder, int position, @NonNull ItemsDataClass model) {
        holder.productName.setText(model.getName());
        holder.stars.setRating(model.getStars());
        holder.price.setText("Rs. " + model.getPrice());
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
//           itemView.setOnClickListener(new View.OnClickListener(){
//
//               @Override
//               public void onClick(View v) {
//                   if(mListener != null){
//                       int position = getAdapterPosition();
//                       if(position != RecyclerView.NO_POSITION){
//                           mListener.onItemClick(,position);
//                       }
//                   }
//               }
//           });
        }

//        @Override
//        public void onClick(View v) {
//            final ArrayList<ItemsDataClass> items = new ArrayList<>();
//            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//            ref.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
//                        items.add(snapshot.getValue(ItemsDataClass.class));
//                    }
//
//                    int position = getLayoutPosition();
//
//                    Intent intent = new Intent(mContext, Item_description.class);
//                    intent.putExtra("name", );
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//        }
    }


}




