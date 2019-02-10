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
import com.example.potap.shopwindow.dbObjects.News;

import java.util.Collections;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    class NewsViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameItemView;
        private final ImageView image;
        private final MaterialCardView materialCardView;


        private NewsViewHolder(View itemView) {
            super(itemView);
            nameItemView = itemView.findViewById(R.id.news_text_view);
            image = itemView.findViewById(R.id.news_image);
            materialCardView = itemView.findViewById(R.id.news_material_card);

            materialCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SneakersActivity.class);
                    intent.putExtra("title",nameItemView.getText().toString());
                    intent.putExtra("newsId", mNews.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }

    private final LayoutInflater mInflater;
    private Context context;
    private List<News> mNews = Collections.emptyList(); // Cached copy of words

    public NewsAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.news, parent, false);
        return new NewsAdapter.NewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder holder, int position) {
        final Context context = mInflater.getContext();
        final News current = mNews.get(position);
        Glide.with(context).asBitmap().load(current.getImage()).into(holder.image);
        holder.nameItemView.setText(current.getTitle());
    }


    public void setNews(List<News> News) {
        mNews = News;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }
}

