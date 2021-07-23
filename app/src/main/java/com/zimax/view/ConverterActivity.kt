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

    companion object {
        const val EXTRA_ITEM = "extra_item_cur"
    }

    private lateinit var leftCurrencySpinner: Spinner
    private lateinit var rightCurrencySpinner: Spinner
    private lateinit var rightTextView: TextView
    private lateinit var leftTextView: EditText

    private lateinit var converterViewModel: ConverterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)
        title = "Конвертер валют"

        leftTextView = findViewById(R.id.leftConverterEditTextNumber)
        rightTextView = findViewById(R.id.rightConverterTextView)

        val button = findViewById<Button>(R.id.button)
        val changeButton = findViewById<FloatingActionButton>(R.id.changeButton)
        changeButton.setOnClickListener { changeCurrencyPlace() }
        button.setOnClickListener { converterResult() }

        converterViewModel = ViewModelProvider(this).get(ConverterViewModel::class.java)
        converterViewModel.getDataConverterResult()
            .observe(this, { integer -> rightTextView.setText(integer.toString()) })
        converterViewModel.currencyList.clear()
        converterViewModel.currencyList.addAll(intent.getSerializableExtra(EXTRA_ITEM) as ArrayList<Currency>)

        leftCurrencySpinner = findViewById(R.id.leftConverterSpinner)
        leftCurrencySpinner.onItemSelectedListener = this
        rightCurrencySpinner = findViewById(R.id.rightConverterSpinner)
        rightCurrencySpinner.onItemSelectedListener = this
        converterViewModel.createSpinner()
        createAdapter()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
        replaceFlagAfterChoose()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    private fun replaceFlagAfterChoose() {
        val leftImageView = findViewById<ImageView>(R.id.leftConverterImageView)
        val rightImageView = findViewById<ImageView>(R.id.rightConverterImageView)

        converterViewModel.ChangeFlagInSpinner(
            leftCurrencySpinner.selectedItem.toString(),
            rightCurrencySpinner.selectedItem.toString()
        )

        converterViewModel.getLeftImageViewLive()
            .observe(this, { integer -> leftImageView.setImageResource(integer) })
        converterViewModel.getRightImageViewLive()
            .observe(this, { integer -> rightImageView.setImageResource(integer) })

    }

    private fun createAdapter() {
        val spinnerAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, converterViewModel.spinnerList)
        spinnerAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)

        leftCurrencySpinner.adapter = spinnerAdapter
        rightCurrencySpinner.adapter = spinnerAdapter
    }

    private fun changeCurrencyPlace() {
        val leftChoose = leftCurrencySpinner.selectedItemPosition
        val rightChoose = rightCurrencySpinner.selectedItemPosition

        leftCurrencySpinner.setSelection(rightChoose)
        rightCurrencySpinner.setSelection(leftChoose)
    }

    private fun converterResult() {
        converterViewModel.converterInViewModel(
            leftCurrencySpinner.selectedItem.toString(),
            rightCurrencySpinner.selectedItem.toString(),
            leftTextView.text.toString().toInt()
        )
    }
}