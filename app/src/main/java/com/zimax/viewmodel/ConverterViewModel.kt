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

    var SpinnerList: List<String> = arrayListOf()

    private val leftImageViewLive = MutableLiveData<Int>()
    private val rightImageViewLive = MutableLiveData<Int>()

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
                    convertInRub = (enteredNumber / currencyList[i].currencyNominal.toString()
                        .toInt() * currencyList[i].currencyValue.toString().toFloat()).toInt()
                    //Log.d("myLOG", convertInRub + "");
                    for (j in currencyList.indices) {
                        if (rightChooseCurrency == (currencyList[j].currencyTicker + " ("
                                    + currencyList[j].currencyName + ")")
                        ) {
                            convertInCur =
                                (convertInRub * currencyList[j].currencyNominal.toString()
                                    .toInt() / currencyList[j].currencyValue.toString()
                                    .toFloat()).toInt()
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
            //этот момент SpinnerList.add...
            (SpinnerList as ArrayList<String>).add(
                i, currencyList[i].currencyTicker + " ("
                        + currencyList[i].currencyName + ")"
            )
        }
    }


    fun getLeftImageViewLive(): LiveData<Int> {
        return leftImageViewLive
    }

    fun getRightImageViewLive(): LiveData<Int> {
        return rightImageViewLive
    }


    fun ChangeFlagInSpinner(leftCurrencySpinner: String, rightCurrencySpinner: String) {

        val leftChooseCurrency: String = leftCurrencySpinner
        val rightChooseCurrency: String = rightCurrencySpinner

        for (i in currencyList.indices) {
            if (leftChooseCurrency == (currencyList[i].currencyTicker + " ("
                        + currencyList[i].currencyName + ")")
            ) {

                leftImageViewLive.value = currencyList[i].currencyFlag
                // leftImageView.setImageResource(currencyList[i].currencyFlag)
            }
            if (rightChooseCurrency == (currencyList[i].currencyTicker + " ("
                        + currencyList[i].currencyName + ")")
            ) {
                rightImageViewLive.value = currencyList[i].currencyFlag
                // rightImageView.setImageResource(currencyList[i].currencyFlag)
            }
        }
    }


}