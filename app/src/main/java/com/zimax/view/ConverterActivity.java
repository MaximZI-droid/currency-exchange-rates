package com.zimax.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zimax.R;
import com.zimax.bussiness.Convertation;
import com.zimax.models.Currency;

import java.util.ArrayList;
import java.util.List;

public class ConverterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_ITEM = "extra_item_cur";

    private List<Currency> currencyList;
    private Spinner leftCurrencySpinner;
    private Spinner rightCurrencySpinner;
    private List<String> SpinnerList;

    private String leftChooseCurrency;
    private String rightChooseCurrency;
    private EditText firstTextView;
    private TextView secondTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        setTitle("Конвертер валют");

        firstTextView = findViewById(R.id.leftConverterEditTextNumber);
        secondTextView = findViewById(R.id.rightConverterTextView);

        currencyList = (ArrayList<Currency>) getIntent().getSerializableExtra(EXTRA_ITEM);

        createSpinners();
        createAdapter();

        FloatingActionButton changeButton = findViewById(R.id.changeButton);

        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        replaceFlagAfterChoose();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void convert(View view) {
        new Convertation().convertation(leftChooseCurrency, rightChooseCurrency,
                currencyList, firstTextView, secondTextView, leftCurrencySpinner, rightCurrencySpinner);
    }

    private void replaceFlagAfterChoose() {
        ImageView leftImageView = findViewById(R.id.leftConverterImageView);
        ImageView rightImageView = findViewById(R.id.rightConverterImageView);

        leftChooseCurrency = leftCurrencySpinner.getSelectedItem().toString();
        rightChooseCurrency = rightCurrencySpinner.getSelectedItem().toString();

        for (int i = 0; i < currencyList.size(); i++) {
            if (leftChooseCurrency.equals(currencyList.get(i).getCurrencyTicker() + " ("
                    + currencyList.get(i).getCurrencyName() + ")")) {
                leftImageView.setImageResource(currencyList.get(i).getCurrencyFlag());
            }
            if (rightChooseCurrency.equals(currencyList.get(i).getCurrencyTicker() + " ("
                    + currencyList.get(i).getCurrencyName() + ")")) {
                rightImageView.setImageResource(currencyList.get(i).getCurrencyFlag());
            }
        }
    }

    private void createSpinners() {
        leftCurrencySpinner = findViewById(R.id.leftConverterSpinner);
        leftCurrencySpinner.setOnItemSelectedListener(this);
        rightCurrencySpinner = findViewById(R.id.rightConverterSpinner);
        rightCurrencySpinner.setOnItemSelectedListener(this);

        SpinnerList = new ArrayList<>();

        for (int i = 0; i < currencyList.size(); i++) {
            SpinnerList.add(i, currencyList.get(i).getCurrencyTicker() + " ("
                    + currencyList.get(i).getCurrencyName() + ")");
        }
    }

    private void createAdapter() {
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, SpinnerList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        leftCurrencySpinner.setAdapter(spinnerAdapter);
        rightCurrencySpinner.setAdapter(spinnerAdapter);
    }

    private void change() {
        int leftChoose = leftCurrencySpinner.getSelectedItemPosition();
        int rightChoose = rightCurrencySpinner.getSelectedItemPosition();

        leftCurrencySpinner.setSelection(rightChoose);
        rightCurrencySpinner.setSelection(leftChoose);
    }
}