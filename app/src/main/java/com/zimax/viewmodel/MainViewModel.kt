package com.zimax.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zimax.api.CurrencyRepository
import com.zimax.models.Currency
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

private const val LOG_TAG = "myLOG"

class MainViewModel : ViewModel() {

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
        val currencyRepository = CurrencyRepository()
        currencyRepository
            .currencyList
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