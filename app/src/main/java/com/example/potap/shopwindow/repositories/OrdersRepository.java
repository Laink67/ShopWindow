package com.example.potap.shopwindow.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.potap.shopwindow.dbObjects.Orders;
import com.example.potap.shopwindow.interfaces.OrdersDAO;

import java.util.List;

public class OrdersRepository {
    private static OrdersRepository instance;
    private OrdersDAO mOrdersDao;
    private LiveData<List<Orders>> mAllOrders;
    private LiveData<Integer> ordersCount;
    private LiveData<Integer> ordersSum;

    public static OrdersRepository getInstance(Application application){
        if(instance == null)
            instance = new OrdersRepository(application);
        return instance;
    }

    private OrdersRepository(Application application) {
        DataManager db = DataManager.getDatabase(application);
        mOrdersDao = db.ordersDAO();
        mAllOrders = mOrdersDao.getOrders();
        ordersCount = mOrdersDao.getCount();
        ordersSum = mOrdersDao.getSum();
    }

    public LiveData<List<Orders>> getAllOrders() {
        return mAllOrders;
    }

    public LiveData<Integer> getCount(){ return ordersCount;}

    public LiveData<Integer> getSum() {return ordersSum;}

    public void insert(Orders orders) {
        new InsertAsyncTask(mOrdersDao).execute(orders);
    }

    private static class InsertAsyncTask extends AsyncTask<Orders, Void, Void> {

        private OrdersDAO mAsyncTaskDao;

        InsertAsyncTask(OrdersDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Orders... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
