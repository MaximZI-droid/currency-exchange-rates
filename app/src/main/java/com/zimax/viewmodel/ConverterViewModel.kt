package com.zimax.viewmodel

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zimax.models.Currency
import java.util.ArrayList

private const val LOG_TAG = "myLOG"

class ConverterViewModel : ViewModel() {

    private val dataConverterResult = MutableLiveData<Int>()
    val currencyList: MutableList<Currency> = mutableListOf()
    var spinnerList: MutableList<String> = mutableListOf()

    private val leftImageLive = MutableLiveData<Int>()
    private val rightImageLive = MutableLiveData<Int>()

    fun converterInViewModel(
        leftChooseCurrency: String,
        rightChooseCurrency: String,
        enteredNumber: Int
    ) {
        try {
            var convertInRub: Int
            var convertInCur = 0
            for (i in currencyList.indices) {
                if (leftChooseCurrency == (currencyList[i].currencyTicker + " ("
                            + currencyList[i].currencyName + ")")
                ) {
                    convertInRub =
                        (enteredNumber / currencyList[i].currencyNominal.toInt() * currencyList[i].currencyValue.toFloat()).toInt()
                    //Log.d("myLOG", convertInRub + "");
                    for (j in currencyList.indices) {
                        if (rightChooseCurrency == (currencyList[j].currencyTicker + " ("
                                    + currencyList[j].currencyName + ")")
                        ) {
                            convertInCur =
                                (convertInRub * currencyList[j].currencyNominal.toInt() / currencyList[j].currencyValue.toFloat()).toInt()
                            //Log.d("myLOG", convertInCur + "");
                        }
                    }
                }
                dataConverterResult.value = convertInCur
            }
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
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