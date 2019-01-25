package com.example.potap.shopwindow.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.potap.shopwindow.R;
import com.example.potap.shopwindow.dbObjects.Categories;

import java.util.Collections;
import java.util.List;


public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

class CategoriesViewHolder extends RecyclerView.ViewHolder {
    private final TextView nameItemView;
    private final ImageView image;
    private final RelativeLayout parentLayout;


    private CategoriesViewHolder(View itemView) {
        super(itemView);
        nameItemView = itemView.findViewById(R.id.news_text_view);
        image = itemView.findViewById(R.id.news_image);
        parentLayout = itemView.findViewById(R.id.news_parent_layout);
    }
}

    private final LayoutInflater mInflater;
    private List<Categories> mCategories = Collections.emptyList(); // Cached copy of words

    public CategoriesAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.news, parent, false);
        return new CategoriesAdapter.CategoriesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        final Context context = mInflater.getContext();
        final Categories current = mCategories.get(position);
        Glide.with(context).asBitmap().load(current.getImage()).into(holder.image);
        holder.nameItemView.setText(current.getTitle());

//        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, InfoActivity.class);
//
//                Bundle bundle = new Bundle();
//                bundle.putSerializable(EXTRA_OBJECTS,current);
//
//                intent.putExtras(bundle);
//                context.startActivity(intent);
//            }
//        });
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