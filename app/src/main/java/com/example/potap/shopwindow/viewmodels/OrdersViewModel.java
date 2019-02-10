package com.example.potap.shopwindow.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.potap.shopwindow.dbObjects.Orders;
import com.example.potap.shopwindow.repositories.OrdersRepository;

import java.util.List;

public class OrdersViewModel extends AndroidViewModel {

    private OrdersRepository mRepository;
    private LiveData<List<Orders>> mAllOrders;
    private LiveData<Integer> mOrdersSum;

    public OrdersViewModel(Application application) {
        super(application);
        mRepository = OrdersRepository.getInstance(application);
        mAllOrders = mRepository.getAllOrders();
        mOrdersSum = mRepository.getSum();
    }

    public LiveData<List<Orders>> getAllOrders() {
        return mAllOrders;
    }

    public LiveData<Integer> getOrdersSum() {
        return mOrdersSum;
    }

    public LiveData<Integer> getResultQuantity() {
        return mRepository.getCount();
    }

    public void insert(Orders orders) {
        mRepository.insert(orders);
    }

    public void delete(int id) {
        mRepository.delete(id);
    }
}
