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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sneakers_activity);
        TextView titleTextView = findViewById(R.id.sneakers_title);

        setUpToolbar();

        title = getIntent().getExtras().getString("title");
        titleTextView.setText(title);

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
            case "Футбол":
                getSportSneakers(0);
                break;
            case "Баскетбол":
                getSportSneakers(1);
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

    private void getSportSneakers(int sport) {
        sneakersViewModel.getSportSneakers(sport).observe(this, new Observer<List<Sneakers>>() {
            @Override
            public void onChanged(@Nullable List<Sneakers> sportSneakers) {
                adapter.setSneakers(sportSneakers);
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
        if (title.equals("Футбол")|| title.equals("Баскетбол"))
            return getMenuSport(item);
        else
            return getMenuForMaleAndFemale(item);
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

    private boolean getMenuSport(MenuItem item) {
        final int sport = title.equals("Футбол") ? 0 : 1;

        switch (item.getItemId()) {
            case R.id.bascet:
                goToBasket();
                return true;
            case R.id.item_sort_a_z:
                sneakersViewModel.getSportSorted(sport, "name").observe(this, new Observer<List<Sneakers>>() {
                    @Override
                    public void onChanged(@Nullable List<Sneakers> sneakers) {
                        adapter.setSneakers(sneakers);
                    }
                });
                return true;
            case R.id.item_sort_price:
                sneakersViewModel.getSportSorted(sport, "price").observe(this, new Observer<List<Sneakers>>() {
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

}
