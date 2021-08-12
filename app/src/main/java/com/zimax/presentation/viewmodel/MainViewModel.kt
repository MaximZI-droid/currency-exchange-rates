package com.zimax.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zimax.data.api.CurrencyRepository
import com.zimax.domain.models.Currency
import com.zimax.domain.usecase.CurrencyLoadUseCase
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

private const val LOG_TAG = "myLOG"

class MainViewModel(private val currencyRepository: CurrencyRepository) : ViewModel() {


    private val dataListCurrency = MutableLiveData<List<Currency>>()
    private var disposableCurrencyRepository: Disposable? = null

    init {
        loadData()
    }

    override fun onCleared() {
        disposableCurrencyRepository?.dispose()
        super.onCleared()
    }

    fun getDataListCurrency(): LiveData<List<Currency>> {
        return dataListCurrency
    }

    private fun loadData() {
        currencyRepository
            .getCurrencyList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<Currency>> {
                override fun onSubscribe(d: Disposable) {
                    disposableCurrencyRepository = d
                }

                override fun onSuccess(currencies: List<Currency>) {
                    dataListCurrency.value = currencies
                }

                override fun onError(e: Throwable) {
                    Log.d(LOG_TAG, "onError $e")
                }
            })
    }
}