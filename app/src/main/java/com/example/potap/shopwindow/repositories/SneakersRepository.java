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
        DataManger db = DataManger.getDatabase(application);
        mSneakersDao = db.sneakersDAO();
        mAllSneakers = mSneakersDao.getSneakers();
    }

    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Sneakers>> getAllSneakers() {
        return mAllSneakers;
    }

    public void insert(Sneakers sneakers) {
        new insertAsyncTask(mSneakersDao).execute(sneakers);
    }

    private static class insertAsyncTask extends AsyncTask<Sneakers, Void, Void> {

        private SneakersDAO mAsyncTaskDao;

        insertAsyncTask(SneakersDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Sneakers... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
