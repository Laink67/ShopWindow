package com.example.potap.shopwindow.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.potap.shopwindow.dbObjects.Sneakers;
import com.example.potap.shopwindow.repositories.SneakersRepository;

import java.util.List;

public class SneakersViewModel extends AndroidViewModel {

    private SneakersRepository mRepository;
    private LiveData<List<Sneakers>> mAllSneakers;

    public SneakersViewModel(Application application) {
        super(application);
        mRepository = new SneakersRepository(application);
        mAllSneakers = mRepository.getAllSneakers();
    }

    public LiveData<List<Sneakers>> getAllSneakers() {
        return mAllSneakers;
    }

    public void insert(Sneakers sneakers) {
        mRepository.insert(sneakers);
    }
}