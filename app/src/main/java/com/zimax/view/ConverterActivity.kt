package com.zimax.view

import androidx.appcompat.app.AppCompatActivity
import android.widget.AdapterView.OnItemSelectedListener
import com.zimax.viewmodel.ConverterViewModel
import android.os.Bundle
import android.view.View
import android.widget.*
import com.zimax.R
import com.zimax.view.ConverterActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.zimax.models.Currency
import java.util.ArrayList

class ConverterActivity : AppCompatActivity(), OnItemSelectedListener {

    private var currencyList: List<Currency>? = null
    private var leftCurrencySpinner: Spinner? = null
    private var rightCurrencySpinner: Spinner? = null
    private var SpinnerList: List<String>? = null
    private var leftChooseCurrency: String? = null
    private var rightChooseCurrency: String? = null
    private var rightTextView: TextView? = null
    private var leftTextView: EditText? = null
    private var converterViewModel: ConverterViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)
        title = "Конвертер валют"

        leftTextView = findViewById(R.id.leftConverterEditTextNumber)
        val button = findViewById<Button>(R.id.button)
        rightTextView = findViewById(R.id.rightConverterTextView)

        currencyList = intent.getSerializableExtra(EXTRA_ITEM) as ArrayList<Currency>?

        converterViewModel = ViewModelProvider(this).get(ConverterViewModel::class.java)
        converterViewModel!!.dataConverterResult.observe(this, { integer -> rightTextView.setText(integer.toString()) })

        createSpinners()
        createAdapter()

        val changeButton = findViewById<FloatingActionButton>(R.id.changeButton)
        changeButton.setOnClickListener { changeCurrencyPlace() }
        button.setOnClickListener { converterResult }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
        replaceFlagAfterChoose()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
    private fun replaceFlagAfterChoose() {
        val leftImageView = findViewById<ImageView>(R.id.leftConverterImageView)
        val rightImageView = findViewById<ImageView>(R.id.rightConverterImageView)

        leftChooseCurrency = leftCurrencySpinner!!.selectedItem.toString()
        rightChooseCurrency = rightCurrencySpinner!!.selectedItem.toString()

        for (i in currencyList!!.indices) {
            if (leftChooseCurrency == (currencyList!![i].currencyTicker + " ("
                            + currencyList!![i].currencyName + ")")) {
                leftImageView.setImageResource(currencyList!![i].currencyFlag)
            }
            if (rightChooseCurrency == (currencyList!![i].currencyTicker + " ("
                            + currencyList!![i].currencyName + ")")) {
                rightImageView.setImageResource(currencyList!![i].currencyFlag)
            }
        }
    }

    private fun createSpinners() {
        leftCurrencySpinner = findViewById(R.id.leftConverterSpinner)
        leftCurrencySpinner.setOnItemSelectedListener(this)
        rightCurrencySpinner = findViewById(R.id.rightConverterSpinner)
        rightCurrencySpinner.setOnItemSelectedListener(this)

        SpinnerList = ArrayList()

        for (i in currencyList!!.indices) {
            SpinnerList.add(i, currencyList!![i].currencyTicker + " ("
                    + currencyList!![i].currencyName + ")")
        }
    }

    private fun createAdapter() {
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, SpinnerList)
        spinnerAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)

        leftCurrencySpinner!!.adapter = spinnerAdapter
        rightCurrencySpinner!!.adapter = spinnerAdapter
    }

    private fun changeCurrencyPlace() {
        val leftChoose = leftCurrencySpinner!!.selectedItemPosition
        val rightChoose = rightCurrencySpinner!!.selectedItemPosition

        leftCurrencySpinner!!.setSelection(rightChoose)
        rightCurrencySpinner!!.setSelection(leftChoose)
    }

    private val converterResult: Unit
        private get() {
            converterViewModel!!.converterInViewModel(leftChooseCurrency.toString(), rightChooseCurrency.toString(), currencyList, leftTextView!!.text.toString().toInt())
        }

    companion object {
        const val EXTRA_ITEM = "extra_item_cur"
    }
}