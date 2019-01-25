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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.potap.shopwindow.R;
import com.example.potap.shopwindow.adapter.SneakersListAdapter;
import com.example.potap.shopwindow.dbObjects.Sneakers;
import com.example.potap.shopwindow.viewmodels.SneakersViewModel;

import java.util.List;

public class ProductsTab extends Fragment {

    private SneakersViewModel mSneakersViewModel;
    private SneakersListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_fragment,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        adapter = new SneakersListAdapter(this.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        // Get a new or existing ViewModel from the ViewModelProvider.
        mSneakersViewModel = ViewModelProviders.of(this).get(SneakersViewModel.class);


        // Add an observer on the LiveData returned by getAll.
        // The onChanged() method fires when the observed data changes and the activity is in the foreground.
        mSneakersViewModel.getAllSneakers().observe(ProductsTab.this, new Observer<List<Sneakers>>() {//replace getAll on mAll
            @Override
            public void onChanged(@Nullable final List<Sneakers> sneakers) {
                // Update the cached copy of the sneakers in the adapter.
                adapter.setSneakers(sneakers);
            }
        });

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_sort_a_z: {
                mSneakersViewModel.getSorted("name").observe(ProductsTab.this, new Observer<List<Sneakers>>() {
                    @Override
                    public void onChanged(@Nullable List<Sneakers> sneakers) {
                        adapter.setSneakers(sneakers);
                    }
                });
                return true;
            }
            case R.id.item_sort_price: {
                mSneakersViewModel.getSorted("price").observe(ProductsTab.this, new Observer<List<Sneakers>>() {
                    @Override
                    public void onChanged(@Nullable List<Sneakers> sneakers) {
                        adapter.setSneakers(sneakers);
                    }
                });
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
