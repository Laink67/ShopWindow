package com.example.potap.shopwindow.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.potap.shopwindow.R;
import com.example.potap.shopwindow.adapter.ViewPagerAdapter;
import com.example.potap.shopwindow.dbObjects.Sneakers;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {

    public static final String EXTRA_OBJECTS = "SneakersObjects";

    private ArrayList<String> imagesLink = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        setUpToolbar();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        Sneakers sneakers = (Sneakers) bundle.getSerializable(EXTRA_OBJECTS);

        imagesLink = sneakers.getImagesLinks();

//        imagesLink.addAll(arr);

        ViewPager viewPager = findViewById(R.id.view_pager);
        DotsIndicator dotsIndicator = findViewById(R.id.dots_indicator);

        ViewPagerAdapter adapter = new ViewPagerAdapter(InfoActivity.this, imagesLink);
        viewPager.setAdapter(adapter);
        dotsIndicator.setViewPager(viewPager);

        TextView textView = findViewById(R.id.text_view_info);
        TextView nameTextView = findViewById(R.id.name_info_text_view);
        TextView priceTextView = findViewById(R.id.price_info_text_view);

        nameTextView.setText(sneakers.getName());
        priceTextView.setText(String.valueOf(sneakers.getPrice()));
        textView.setText(sneakers.getDescription());
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
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bascet:
                Intent intent = new Intent(this,BasketActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
