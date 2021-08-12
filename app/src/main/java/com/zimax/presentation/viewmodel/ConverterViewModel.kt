package com.zimax.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zimax.domain.models.Currency
import com.zimax.domain.usecase.ConvertationUseCase

private const val LOG_TAG = "myLOG"

class ConverterViewModel : ViewModel() {

    private var dataConverterResult = MutableLiveData<Int>()
    val currencyList: MutableList<Currency> = mutableListOf()
    var spinnerList: MutableList<String> = mutableListOf()
    val convertationUseCase = ConvertationUseCase()

    private val leftImageLive = MutableLiveData<Int>()
    private val rightImageLive = MutableLiveData<Int>()

    fun converterInViewModel(
        leftChooseCurrency: String,
        rightChooseCurrency: String,
        enteredNumber: Int
    ) {
        dataConverterResult = convertationUseCase.convertCurrency(
            leftChooseCurrency = leftChooseCurrency,
            rightChooseCurrency = rightChooseCurrency,
            enteredNumber = enteredNumber
        )
    }

    fun getDataConverterResult(): LiveData<Int> {
        return dataConverterResult
    }

    fun createSpinner() {
        for (i in currencyList.indices) {
            spinnerList.add(
                i, currencyList[i].currencyTicker + " ("
                        + currencyList[i].currencyName + ")"
            )
        }
    }

    fun getLeftImageViewLive(): LiveData<Int> {
        return leftImageLive
    }

    fun getRightImageViewLive(): LiveData<Int> {
        return rightImageLive
    }

    fun changeFlagInSpinner(leftCurrencySpinner: String, rightCurrencySpinner: String) {

        val leftChooseCurrency: String = leftCurrencySpinner
        val rightChooseCurrency: String = rightCurrencySpinner

        for (i in currencyList.indices) {
            if (leftChooseCurrency == (currencyList[i].currencyTicker + " ("
                        + currencyList[i].currencyName + ")")
            ) {
                leftImageLive.value = currencyList[i].currencyFlag
            }
            if (rightChooseCurrency == (currencyList[i].currencyTicker + " ("
                        + currencyList[i].currencyName + ")")
            ) {
                rightImageLive.value = currencyList[i].currencyFlag
            }
        }
    }
}