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
    private LiveData<List<Sizes>> mAllSizes;

    public SizesViewModel(@NonNull Application application) {
        super(application);
        mRepository = SizesRepository.getInstance(application);
        mAllSizes = mRepository.getAll();
    }

    public LiveData<List<Sizes>> getAll() {
        mAllSizes = mRepository.getAll();
        return mAllSizes;
    }

    public LiveData<List<Sizes>> getBySneakersId(int id) {
        mAllSizes = mRepository.getBySneakersId(id);
        return mAllSizes;
    }
}
