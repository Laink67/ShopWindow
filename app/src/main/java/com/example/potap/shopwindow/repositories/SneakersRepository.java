package com.example.potap.shopwindow.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.potap.shopwindow.dbObjects.Sneakers;
import com.example.potap.shopwindow.interfaces.SneakersDAO;

import java.util.List;

public class SneakersRepository {
    private static SneakersRepository instance;
    private SneakersDAO mSneakersDao;
    private LiveData<List<Sneakers>> mAllSneakers;

    public static SneakersRepository getInstance(Application application) {
        if (instance == null)
            instance = new SneakersRepository(application);
        return instance;
    }

    private SneakersRepository(Application application) {
        DataManager db = DataManager.getDatabase(application);
        mSneakersDao = db.sneakersDAO();
        mAllSneakers = mSneakersDao.getAll();
    }

    public LiveData<List<Sneakers>> getByCategoriesId(int id) {
        mAllSneakers = mSneakersDao.getByCategoriesId(id);
        return mAllSneakers;
    }

    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Sneakers>> getAllSneakers() {
        mAllSneakers = mSneakersDao.getAll();
        return mAllSneakers;
    }

    public LiveData<List<Sneakers>> getByNewsId(int id) {
        mAllSneakers = mSneakersDao.getByNewsId(id);
        return mAllSneakers;
    }

    public void insert(Sneakers sneakers) {
        new InsertAsyncTask(mSneakersDao).execute(sneakers);
    }

    public LiveData<List<Sneakers>> getSortedWithCategoriesId(int categoriesId, String column) {
        mAllSneakers = column.equals("name") ? mSneakersDao.getSortedWithCategoriesIdByName(categoriesId) :
                mSneakersDao.getSortedWithCategoriesIdByPrice(categoriesId);
        return mAllSneakers;
    }

    public LiveData<List<Sneakers>> getSortedWithNewsId(int newsId, String column) {
        mAllSneakers = column.equals("name") ? mSneakersDao.getSortedWithNewsIdByName(newsId) :
                mSneakersDao.getSortedWithNewsIdByPrice(newsId);
        return mAllSneakers;
    }

    public LiveData<List<Sneakers>> getSorted(int gender, boolean child, String column) {
        if (child)
            mAllSneakers = column.equals("name") ? mSneakersDao.getSortedChildrenByName(true) : mSneakersDao.getSortedChildrenByPrice(true);
        else
            mAllSneakers = column.equals("name") ? mSneakersDao.getSortedByName(gender, false) : mSneakersDao.getSortedByPrice(gender, false);
        return mAllSneakers;
    }

    public LiveData<List<Sneakers>> sort(String column) {

        if (column.equals("name"))
            mAllSneakers = mSneakersDao.getSortedByName();
        else
            mAllSneakers = mSneakersDao.getSortedByPrice();

        return mAllSneakers;
    }

    public LiveData<List<Sneakers>> search(String searchString) {
        searchString = "%" + searchString + "%";
        mAllSneakers = mSneakersDao.search(searchString);
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
