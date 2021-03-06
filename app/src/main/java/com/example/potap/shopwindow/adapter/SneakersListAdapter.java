package com.example.potap.shopwindow.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.potap.shopwindow.R;
import com.example.potap.shopwindow.activities.InfoActivity;
import com.example.potap.shopwindow.dbObjects.Sneakers;

import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.potap.shopwindow.activities.InfoActivity.EXTRA_OBJECTS;


public class SneakersListAdapter extends RecyclerView.Adapter<SneakersListAdapter.SneakersViewHolder> {

    class SneakersViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameItemView, priceItemView;
        private final CircleImageView image;
        private final RelativeLayout parentLayout;

        private SneakersViewHolder(View itemView) {
            super(itemView);
            nameItemView = itemView.findViewById(R.id.text_view);
            priceItemView = itemView.findViewById(R.id.text_view_price);
            image = itemView.findViewById(R.id.image);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

    private final LayoutInflater mInflater;
    private List<Sneakers> mSneakers = Collections.emptyList(); // Cached copy of words

    public SneakersListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SneakersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new SneakersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SneakersViewHolder holder, int position) {
        final Context context = mInflater.getContext();
        final Sneakers current = mSneakers.get(position);
        Glide.with(context).asBitmap().load(current.getImage()).into(holder.image);
        holder.nameItemView.setText(current.getName());
        holder.priceItemView.setText(String.valueOf(current.getPrice()));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InfoActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable(EXTRA_OBJECTS, current);

                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    public void setSneakers(List<Sneakers> sneakers) {
        mSneakers = sneakers;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mSneakers.size();
    }
}

