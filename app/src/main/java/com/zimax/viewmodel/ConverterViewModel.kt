package com.zimax.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zimax.models.Currency

class ConverterViewModel : ViewModel() {

    private val dataConverterResult = MutableLiveData<Int>()
    fun converterInViewModel(leftChooseCurrency: String, rightChooseCurrency: String,
                             currencyList: List<Currency>, enteredNumber: Int) {
        try {
            var convertInRub: Int
            var convertInCur = 0
            for (i in currencyList.indices) {
                if (leftChooseCurrency == (currencyList[i].currencyTicker + " ("
                                + currencyList[i].currencyName + ")")) {
                    convertInRub = (enteredNumber / currencyList[i].currencyNominal.toString().toInt() * currencyList[i].currencyValue.toString().toFloat()).toInt()
                    //Log.d("myLOG", convertInRub + "");
                    for (j in currencyList.indices) {
                        if (rightChooseCurrency == (currencyList[j].currencyTicker + " ("
                                        + currencyList[j].currencyName + ")")) {
                            convertInCur = (convertInRub * currencyList[j].currencyNominal.toString().toInt() / currencyList[j].currencyValue.toString().toFloat()).toInt()
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

    companion object {
        private const val LOG_TAG = "myLOG"
    }
}