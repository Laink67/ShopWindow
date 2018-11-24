package com.example.potap.shopwindow.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.potap.shopwindow.R;
import com.example.potap.shopwindow.adapter.SneakersListAdapter;
import com.example.potap.shopwindow.dbObjects.Sneakers;
import com.example.potap.shopwindow.viewmodels.SneakersViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_SNEAKERS_ACTIVITY_REQUEST_CODE = 1;

    private SneakersViewModel mSneakersViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.fab);
        ImageButton menuSort = findViewById(R.id.menu_sort);
//        ImageButton btSortName = findViewById(R.id.button_sort_name);
//        Button btSortPrice = findViewById(R.id.button_sort_price);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final SneakersListAdapter adapter = new SneakersListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get a new or existing ViewModel from the ViewModelProvider.
        mSneakersViewModel = ViewModelProviders.of(this).get(SneakersViewModel.class);


        // Add an observer on the LiveData returned by getAll.
        // The onChanged() method fires when the observed data changes and the activity is in the foreground.
        mSneakersViewModel.getAllSneakers().observe(MainActivity.this, new Observer<List<Sneakers>>() {//replace getAll on mAll
            @Override
            public void onChanged(@Nullable final List<Sneakers> sneakers) {
                // Update the cached copy of the sneakers in the adapter.
                adapter.setSneakers(sneakers);
            }
        });

//        btSortName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Update the cached copy of the sneakers in the adapter.
//                adapter.getSorted("name");
//            }
//        });

        menuSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update the cached copy of the sneakers in the adapter.
                showPopup(v,adapter);
            }
        });
        //Sort sneakers by name
//        btSortName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mSneakersViewModel.getSorted("name").observe(MainActivity.this, new Observer<List<Sneakers>>() {
        //                    @Override
//                    public void onChanged(@Nullable final List<Sneakers> sneakers) {
//                        // Update the cached copy of the sneakers in the adapter.
//                        adapter.setSneakers(sneakers);
//                    }
//                });
//            }
//        });

        //Sort sneakers by price
//        btSortPrice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Update the cached copy of the sneakers in the adapter.
//                adapter.getSorted("price");
//            }
//        });

//        btSortPrice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mSneakersViewModel.getSorted("price").observe(MainActivity.this, new Observer<List<Sneakers>>() {
//                    @Override
//                    public void onChanged(@Nullable final List<Sneakers> sneakers) {
//                        // Update the cached copy of the sneakers in the adapter.
//                        adapter.setSneakers(sneakers);
//                    }
//                });
//            }
//        });

        //Add object
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewSneakersActivity.class);
                startActivityForResult(intent, NEW_SNEAKERS_ACTIVITY_REQUEST_CODE);
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_SNEAKERS_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Sneakers sneakers = new Sneakers(data.getStringExtra(NewSneakersActivity.EXTRA_REPLY), 0, "", "", "", "");
            mSneakersViewModel.insert(sneakers);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

    private void showPopup(View v, final SneakersListAdapter adapter) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.popup_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_sort_a_z:
                        adapter.getSorted("name");
                        return true;
                    case R.id.item_sort_price:
                        adapter.getSorted("price");
                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.show();
    }


//    private void getSorted(List<Sneakers> sneakers, final String column) {
//        Collections.sort(sneakers, new Comparator<Sneakers>() {
//            @Override
//            public int compare(Sneakers o1, Sneakers o2) {
//                if (column.equals("name"))
//                    return o1.getName().compareTo(o2.getName());
//                else
//                    return o1.getPrice() - o2.getPrice();
//            }
//        });
//    }
}
