package com.example.potap.shopwindow.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.card.MaterialCardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.potap.shopwindow.R;
import com.example.potap.shopwindow.activities.SneakersActivity;
import com.example.potap.shopwindow.dbObjects.Categories;

import java.util.Collections;
import java.util.List;


public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

    class CategoriesViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameItemView;
        private final ImageView image;
        private final MaterialCardView materialCardView;


        private CategoriesViewHolder(View itemView) {
            super(itemView);
            nameItemView = itemView.findViewById(R.id.categories_text_view);
            image = itemView.findViewById(R.id.categories_image);
            materialCardView = itemView.findViewById(R.id.categories_material_card);

            materialCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SneakersActivity.class);
                    intent.putExtra("title", mCategories.get(getAdapterPosition()).getTitle());
                    intent.putExtra("categoriesId", mCategories.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }


    private final LayoutInflater mInflater;
    private Context context;
    private List<Categories> mCategories = Collections.emptyList(); // Cached copy of words

    public CategoriesAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.categories, parent, false);
        return new CategoriesAdapter.CategoriesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        final Context context = mInflater.getContext();
        final Categories current = mCategories.get(position);
        Glide.with(context).asBitmap().load(current.getImage()).into(holder.image);
        holder.nameItemView.setText(current.getTitle());
    }


    public void setCategories(List<Categories> Categories) {
        mCategories = Categories;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }
}
