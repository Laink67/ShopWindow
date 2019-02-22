package com.example.potap.shopwindow.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.potap.shopwindow.dbObjects.Sizes;
import com.example.potap.shopwindow.repositories.SizesRepository;

import java.util.List;

public class SizesViewModel extends AndroidViewModel {

    private SizesRepository mRepository;

    public SizesViewModel(@NonNull Application application) {
        super(application);
        mRepository = SizesRepository.getInstance(application);
    }

    public LiveData<List<Sizes>> getAll() {
        return mRepository.getAll();
    }

    public LiveData<List<Sizes>> getBySneakersId(int id) {
        return mRepository.getBySneakersId(id);
    }
}
