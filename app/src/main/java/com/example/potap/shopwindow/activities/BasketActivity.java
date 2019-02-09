package com.example.potap.shopwindow.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.potap.shopwindow.R;
import com.example.potap.shopwindow.adapter.OrdersAdapter;
import com.example.potap.shopwindow.dbObjects.Orders;
import com.example.potap.shopwindow.viewmodels.OrdersViewModel;

import java.util.List;

public class BasketActivity extends AppCompatActivity {

    private OrdersViewModel mOrdersViewModel;
    private OrdersAdapter adapter;
    private TextView resultQuantityTextView, resultSumTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basket_activity);
        resultQuantityTextView = findViewById(R.id.basket_all_number);
        resultSumTextView = findViewById(R.id.basket_sum);
        setUpToolbar();

        RecyclerView recyclerView = findViewById(R.id.basket_recyclerview);
        adapter = new OrdersAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get a new or existing ViewModel from the ViewModelProvider.
        mOrdersViewModel = ViewModelProviders.of(this).get(OrdersViewModel.class);

        // Add an observer on the LiveData returned by getAll.
        // The onChanged() method fires when the observed data changes and the activity is in the foreground.
        mOrdersViewModel.getAllOrders().observe(this, new Observer<List<Orders>>() {//replace getAll on mAll
            @Override
            public void onChanged(@Nullable final List<Orders> orders) {
                // Update the cached copy of the sneakers in the adapter.
                adapter.setOrders(orders);
            }
        });

        setOrderResult();  /*Запись результатов заказа(сумма,количество)*/
    }

    private void setUpToolbar() {
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }


    private void setOrderResult() {
        mOrdersViewModel.getResultQuantity().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if (integer != null)
                    resultQuantityTextView.setText(integer.toString());
                else
                    resultQuantityTextView.setText(String.valueOf(0));
            }
        });

        mOrdersViewModel.getOrdersSum().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if (integer != null)
                    resultSumTextView.setText(integer.toString());
                else
                    resultSumTextView.setText(String.valueOf(0));
            }
        });
    }
}
