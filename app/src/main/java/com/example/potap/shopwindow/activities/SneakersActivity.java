package com.example.potap.shopwindow.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.potap.shopwindow.R;
import com.example.potap.shopwindow.adapter.SneakersListAdapter;
import com.example.potap.shopwindow.dbObjects.Sneakers;
import com.example.potap.shopwindow.viewmodels.SneakersViewModel;

import java.util.List;

public class SneakersActivity extends AppCompatActivity {

    private SneakersViewModel sneakersViewModel;
    private SneakersListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sneakers_activity);

        setUpToolbar();

        String title = getIntent().getExtras().getString("title");

        adapter = new SneakersListAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get a new or existing ViewModel from the ViewModelProvider.
        sneakersViewModel = ViewModelProviders.of(this).get(SneakersViewModel.class);

        switch (title) {
            case "Женщинам":
                getFemaleSneakers();
                break;
            case "Мужчинам":
                getMaleSneakers();
                break;
            case "Детям":
                getChildrenSneakers();
                break;
            default:
                getAllSneakers();
                break;
        }

    }

    private void getAllSneakers() {
        sneakersViewModel.getAllSneakers().observe(this, new Observer<List<Sneakers>>() {
            @Override
            public void onChanged(@Nullable List<Sneakers> sneakers) {
                adapter.setSneakers(sneakers);
            }
        });
    }

    private void getFemaleSneakers() {
        sneakersViewModel.getFemaleSneakers().observe(this, new Observer<List<Sneakers>>() {
            @Override
            public void onChanged(@Nullable List<Sneakers> femaleSneakers) {
                adapter.setSneakers(femaleSneakers);
            }
        });
    }

    private void getMaleSneakers() {
        sneakersViewModel.getMaleSneakers().observe(this, new Observer<List<Sneakers>>() {
            @Override
            public void onChanged(@Nullable List<Sneakers> maleSneakers) {
                adapter.setSneakers(maleSneakers);
            }
        });
    }

    private void getChildrenSneakers() {
        sneakersViewModel.getChildrenSneakers().observe(this, new Observer<List<Sneakers>>() {
            @Override
            public void onChanged(@Nullable List<Sneakers> childrenSneakers) {
                adapter.setSneakers(childrenSneakers);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.popup_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bascet:
                Intent intent = new Intent(this, BasketActivity.class);
                startActivity(intent);
                return true;
            case R.id.item_sort_a_z:
                sneakersViewModel.getSorted("name").observe(this, new Observer<List<Sneakers>>() {
                    @Override
                    public void onChanged(@Nullable List<Sneakers> sneakers) {
                        adapter.setSneakers(sneakers);
                    }
                });
                return true;
            case R.id.item_sort_price:
                sneakersViewModel.getSorted("price").observe(this, new Observer<List<Sneakers>>() {
                    @Override
                    public void onChanged(@Nullable List<Sneakers> sneakers) {
                        adapter.setSneakers(sneakers);
                    }
                });
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setUpToolbar() {
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

}
