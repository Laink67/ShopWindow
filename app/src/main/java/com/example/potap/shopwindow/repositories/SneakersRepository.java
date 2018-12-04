package com.example.potap.shopwindow.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.potap.shopwindow.dbObjects.Sneakers;
import com.example.potap.shopwindow.interfaces.SneakersDAO;

import java.util.List;

public class SneakersRepository {
    private SneakersDAO mSneakersDao;
    private LiveData<List<Sneakers>> mAllSneakers;

    public SneakersRepository(Application application) {
        DataManager db = DataManager.getDatabase(application);
        mSneakersDao = db.sneakersDAO();
        mAllSneakers = mSneakersDao.getSneakers();
    }

    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Sneakers>> getAllSneakers() {
        return mAllSneakers;
    }

    public void insert(Sneakers sneakers) {
        new InsertAsyncTask(mSneakersDao).execute(sneakers);
    }

//    public LiveData<List<Sneakers>> getSorted(String column) {
//        mAllSneakers = column.equals("name") ? mSneakersDao.getSortedByName() : mSneakersDao.getSortedByPrice();
//        return mAllSneakers;
//    }

    public LiveData<List<Sneakers>> sort(String column) {

        if (column.equals("name"))
            mAllSneakers = mSneakersDao.getSortedByName();
        else
            mAllSneakers = mSneakersDao.getSortedByPrice();

        return mAllSneakers;
    }

    private static class InsertAsyncTask extends AsyncTask<Sneakers, Void, Void> {

        private SneakersDAO mAsyncTaskDao;

        InsertAsyncTask(SneakersDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Sneakers... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
