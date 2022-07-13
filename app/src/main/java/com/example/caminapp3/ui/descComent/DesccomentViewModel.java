package com.example.caminapp3.ui.descComent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DesccomentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DesccomentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is a fragment");

    }

    public LiveData<String> getText() {
        return mText;
    }
}