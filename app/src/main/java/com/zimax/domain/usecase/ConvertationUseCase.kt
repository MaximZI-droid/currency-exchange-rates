package com.zimax.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.zimax.domain.models.Currency

class ConvertationUseCase {

    private val dataConverterResult = MutableLiveData<Int>()
    val currencyList: MutableList<Currency> = mutableListOf()


    fun convertCurrency(
        leftChooseCurrency: String,
        rightChooseCurrency: String,
        enteredNumber: Int
    ): MutableLiveData<Int> {
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
        return dataConverterResult
    }
}