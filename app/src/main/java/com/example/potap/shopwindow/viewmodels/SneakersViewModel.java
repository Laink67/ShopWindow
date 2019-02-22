package com.example.potap.shopwindow.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.potap.shopwindow.dbObjects.Sneakers;
import com.example.potap.shopwindow.repositories.SneakersRepository;

import java.util.List;

public class SneakersViewModel extends AndroidViewModel {

    private SneakersRepository mRepository;

    public SneakersViewModel(Application application) {
        super(application);
        mRepository = SneakersRepository.getInstance(application);
    }

    public LiveData<List<Sneakers>> getSortedWithCategoriesId(int categoriesId, String column) {
        return mRepository.getSortedWithCategoriesId(categoriesId, column);
    }

    public LiveData<List<Sneakers>> getSortedWithNewsId(int newsId, String column) {
        return mRepository.getSortedWithNewsId(newsId, column);
    }

    public LiveData<List<Sneakers>> getByCategoriesId(int id) {
        return mRepository.getByCategoriesId(id);
    }

    public LiveData<List<Sneakers>> getByNewsId(int id) {
        return mRepository.getByNewsId(id);
    }

    public LiveData<List<Sneakers>> getAllSneakers() {
        return mRepository.getAllSneakers();
    }

    public void insert(Sneakers sneakers) {
        mRepository.insert(sneakers);
    }

    public LiveData<List<Sneakers>> getSorted(String column) {
        return mRepository.sort(column);
    }

    public LiveData<List<Sneakers>> getSorted(int gender, boolean child, String column) {
       return mRepository.getSorted(gender, child, column);
    }

    public LiveData<List<Sneakers>> search(String searchString) {
        return mRepository.search(searchString);
    }

}