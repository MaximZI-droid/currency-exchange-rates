package com.zimax.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zimax.api.CurrencyRepository;
import com.zimax.models.Currency;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {

    public MutableLiveData<List<Currency>> dataListCurrency = new MutableLiveData<>();
    private Disposable disposableCurrencyRepository;
    private static final String LOG_TAG = "myLOG";

    public MainViewModel() {

        CurrencyRepository currencyRepository = new CurrencyRepository();
        currencyRepository
                .getCurrencyList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Currency>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposableCurrencyRepository = d;
                    }

                    @Override
                    public void onSuccess(@NonNull List<Currency> currencies) {
                        dataListCurrency.setValue(currencies);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(LOG_TAG, "onError " + e);
                    }
                });
    }

    @Override
    protected void onCleared() {
        if (disposableCurrencyRepository != null) {
            disposableCurrencyRepository.dispose();
        }
        super.onCleared();
    }
}
