package com.example.potap.shopwindow.activities.mainTabs;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.potap.shopwindow.R;
import com.example.potap.shopwindow.adapter.NewsAdapter;
import com.example.potap.shopwindow.dbObjects.News;
import com.example.potap.shopwindow.viewmodels.NewsViewModel;

import java.util.List;

public class NewsTab extends Fragment {

    private NewsViewModel mNewsViewModel;
    private NewsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.news_recyclerview);
        adapter = new NewsAdapter(this.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        
        // Get a new or existing ViewModel from the ViewModelProvider.
        mNewsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);

        // Add an observer on the LiveData returned by getAll.
        // The onChanged() method fires when the observed data changes and the activity is in the foreground.
        mNewsViewModel.getAllNews().observe(NewsTab.this, new Observer<List<News>>() {//replace getAll on mAll
            @Override
            public void onChanged(@Nullable final List<News> News) {
                // Update the cached copy of the sneakers in the adapter.
                adapter.setNews(News);
            }
        });

        return view;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.item_sort_a_z: {
//                mSneakersViewModel.getSorted("name").observe(NewsTab.this, new Observer<List<Sneakers>>() {
//                    @Override
//                    public void onChanged(@Nullable List<Sneakers> sneakers) {
//                        adapter.setSneakers(sneakers);
//                    }
//                });
//                return true;
//            }
//            case R.id.item_sort_price: {
//                mSneakersViewModel.getSorted("price").observe(NewsTab.this, new Observer<List<Sneakers>>() {
//                    @Override
//                    public void onChanged(@Nullable List<Sneakers> sneakers) {
//                        adapter.setSneakers(sneakers);
//                    }
//                });
//                return true;
//            }
//        }
//        return super.onOptionsItemSelected(item);
//    }

}
