package com.example.potap.shopwindow.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.potap.shopwindow.R;
import com.example.potap.shopwindow.adapter.SneakersListAdapter;
import com.example.potap.shopwindow.dbObjects.Sneakers;
import com.example.potap.shopwindow.viewmodels.SneakersViewModel;

import java.util.List;

public class SneakersActivity extends AppCompatActivity {
    private SneakersViewModel sneakersViewModel;
    private SneakersListAdapter adapter;
    private String title;
    private int newsId, categoriesId;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sneakers_activity);
        TextView titleTextView = findViewById(R.id.sneakers_title);

        setUpToolbar();

        Bundle bundle = getIntent().getExtras();
        newsId = bundle.getInt("newsId");
        categoriesId = bundle.getInt("categoriesId");
        title = bundle.getString("title");
        titleTextView.setText(title);

        adapter = new SneakersListAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get a new or existing ViewModel from the ViewModelProvider.
        sneakersViewModel = ViewModelProviders.of(this).get(SneakersViewModel.class);

        getSneakersForPage();
    }

    private void getSneakersForPage() {

        if (newsId != 0) {
            sneakersViewModel.getByNewsId(newsId).observe(this, new Observer<List<Sneakers>>() {
                @Override
                public void onChanged(@Nullable List<Sneakers> sneakers) {
                    adapter.setSneakers(sneakers);
                }
            });
        } else if (categoriesId != 0) {
            sneakersViewModel.getByCategoriesId(categoriesId).observe(this, new Observer<List<Sneakers>>() {
                @Override
                public void onChanged(@Nullable List<Sneakers> sneakers) {
                    adapter.setSneakers(sneakers);
                }
            });
        } else {
            sneakersViewModel.getAllSneakers().observe(this, new Observer<List<Sneakers>>() {
                @Override
                public void onChanged(@Nullable List<Sneakers> sneakers) {
                    adapter.setSneakers(sneakers);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.popup_menu, menu);
        search(menu.findItem(R.id.item_find));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (categoriesId != 0)
            return getMenuForCategories(item);
        else if (newsId != 0) {
            return getMenuForNews(item);
        } else if (title.equals("Поиск"))
            return getMenuForSearch(item);
        else
            return getMenuForMaleAndFemale(item);
    }

    private boolean getMenuForCategories(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bascet:
                goToBasket();
                return true;
            case R.id.item_sort_a_z:
                sneakersViewModel.getSortedWithCategoriesId(categoriesId, "name").observe(this, new Observer<List<Sneakers>>() {
                    @Override
                    public void onChanged(@Nullable List<Sneakers> sneakers) {
                        adapter.setSneakers(sneakers);
                    }
                });
                return true;
            case R.id.item_sort_price:
                sneakersViewModel.getSortedWithCategoriesId(categoriesId, "price").observe(this, new Observer<List<Sneakers>>() {
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

    private boolean getMenuForNews(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bascet:
                goToBasket();
                return true;
            case R.id.item_sort_a_z:
                sneakersViewModel.getSortedWithNewsId(newsId, "name").observe(this, new Observer<List<Sneakers>>() {
                    @Override
                    public void onChanged(@Nullable List<Sneakers> sneakers) {
                        adapter.setSneakers(sneakers);
                    }
                });
                return true;
            case R.id.item_sort_price:
                sneakersViewModel.getSortedWithNewsId(newsId, "price").observe(this, new Observer<List<Sneakers>>() {
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


    private boolean getMenuForSearch(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.bascet:
                goToBasket();
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

    private boolean getMenuForMaleAndFemale(MenuItem item) {
        final int gender = title.equals("Мужчинам") ? 1 : 0;
        final boolean child = title.equals("Детям");

        switch (item.getItemId()) {
            case R.id.bascet:
                goToBasket();
                return true;
            case R.id.item_sort_a_z:
                sneakersViewModel.getSorted(gender, child, "name").observe(this, new Observer<List<Sneakers>>() {
                    @Override
                    public void onChanged(@Nullable List<Sneakers> sneakers) {
                        adapter.setSneakers(sneakers);
                    }
                });
                return true;
            case R.id.item_sort_price:
                sneakersViewModel.getSorted(gender, child, "price").observe(this, new Observer<List<Sneakers>>() {
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

    private void goToBasket() {
        Intent intent = new Intent(this, BasketActivity.class);
        startActivity(intent);
    }

    private void setUpToolbar() {
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void startSearch(String stringSearch) {
        sneakersViewModel.search(stringSearch).observe(this, new Observer<List<Sneakers>>() {
            @Override
            public void onChanged(@Nullable List<Sneakers> sneakers) {
                adapter.setSneakers(sneakers);
            }
        });
    }

    private void search(MenuItem item) {
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                startSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (newText.equals(""))
                    getSneakersForPage();
                else
                    startSearch(newText);

                return true;
            }
        });
    }

}
