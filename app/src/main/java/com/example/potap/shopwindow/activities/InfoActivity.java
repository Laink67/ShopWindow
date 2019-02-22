package com.example.potap.shopwindow.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.potap.shopwindow.R;
import com.example.potap.shopwindow.adapter.SizesAdapter;
import com.example.potap.shopwindow.adapter.ViewPagerAdapter;
import com.example.potap.shopwindow.dbObjects.Orders;
import com.example.potap.shopwindow.dbObjects.Sizes;
import com.example.potap.shopwindow.dbObjects.Sneakers;
import com.example.potap.shopwindow.viewmodels.OrdersViewModel;
import com.example.potap.shopwindow.viewmodels.SizesViewModel;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;
import java.util.List;

import ru.dimorinny.floatingtextbutton.FloatingTextButton;

public class InfoActivity extends AppCompatActivity {

    public static final String EXTRA_OBJECTS = "SneakersObjects";

    private OrdersViewModel ordersViewModel;
    private SizesAdapter sizesAdapter;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        setUpToolbar();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        final Sneakers sneakers = (Sneakers) bundle.getSerializable(EXTRA_OBJECTS);

        ArrayList<String> imagesLink = sneakers.getImagesLinks();

        ViewPager viewPager = findViewById(R.id.view_pager);
        DotsIndicator dotsIndicator = findViewById(R.id.dots_indicator);
        ViewPagerAdapter adapter = new ViewPagerAdapter(InfoActivity.this, imagesLink);
        viewPager.setAdapter(adapter);
        dotsIndicator.setViewPager(viewPager);

        RecyclerView sizesRecyclerView = findViewById(R.id.sizes_recyclerview);
        sizesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        sizesAdapter = new SizesAdapter(this);
        sizesRecyclerView.setAdapter(sizesAdapter);

        TextView textView = findViewById(R.id.text_view_info);
        TextView nameTextView = findViewById(R.id.name_info_text_view);
        TextView priceTextView = findViewById(R.id.price_info_text_view);
        FloatingTextButton floatingTextButton = findViewById(R.id.add_to_basket_button);
        radioGroup = findViewById(R.id.sizes_radiogroup);

        nameTextView.setText(sneakers.getName());
        priceTextView.setText(String.valueOf(sneakers.getPrice()));
        textView.setText(sneakers.getDescription());

        SizesViewModel sizesViewModel = ViewModelProviders.of(this).get(SizesViewModel.class);
        ordersViewModel = ViewModelProviders.of(this).get(OrdersViewModel.class);


        sizesViewModel.getBySneakersId(sneakers.getId()).observe(this, new Observer<List<Sizes>>() {
            @Override
            public void onChanged(@Nullable List<Sizes> sizes) {
                sizesAdapter.setSizes(sizes);
            }
        });

        floatingTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sizesAdapter.size != 0.0) {
                    ordersViewModel.insert(getOrder(sneakers));

                    Snackbar.make(radioGroup, "Added to cart", Snackbar.LENGTH_SHORT).show();
                } else {
                    Snackbar.make(radioGroup, "You should select size", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Orders getOrder(Sneakers sneakers) {

        Orders order = new Orders(sneakers);
        order.setSize(sizesAdapter.size);

        return order;
    }

    private void setUpToolbar() {
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.simple_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bascet:
                Intent intent = new Intent(this, BasketActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
