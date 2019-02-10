package com.example.potap.shopwindow.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.potap.shopwindow.dbObjects.Categories;
import com.example.potap.shopwindow.interfaces.CategoriesDAO;

import java.util.List;

public class CategoriesRepository {
    private static CategoriesRepository instance;
    private CategoriesDAO mCategoriesDAO;
    private LiveData<List<Categories>> mAllCategories;

    public static CategoriesRepository getInstance(Application application) {
        if (instance == null)
            instance = new CategoriesRepository(application);
        return instance;
    }

    private CategoriesRepository(Application application) {
        DataManager db = DataManager.getDatabase(application);
        mCategoriesDAO = db.categoriesDAO();
        mAllCategories = mCategoriesDAO.getAll();
    }

    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Categories>> getAllCategories() {
        return mAllCategories;
    }

    public void insert(Categories Categories) {
        new CategoriesRepository.InsertAsyncTask(mCategoriesDAO).execute(Categories);
    }

    private static class InsertAsyncTask extends AsyncTask<Categories, Void, Void> {

        private CategoriesDAO mAsyncTaskDao;

        InsertAsyncTask(CategoriesDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Categories... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
