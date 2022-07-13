package com.example.caminapp3.ui.foro;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ForoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ForoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");

    }

    public LiveData<String> getText() {
        return mText;
    }
}