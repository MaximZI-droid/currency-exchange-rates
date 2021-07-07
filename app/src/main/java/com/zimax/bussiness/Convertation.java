package com.zimax.bussiness;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.zimax.models.Currency;

import java.util.List;

public class Convertation {

    public void convertation(String leftChooseCurrency, String rightChooseCurrency,
                             List<Currency> currencyList, EditText firstTextView, TextView secondTextView,
                             Spinner leftCurrencySpinner, Spinner rightCurrencySpinner) {

        leftChooseCurrency = leftCurrencySpinner.getSelectedItem().toString();
        rightChooseCurrency = rightCurrencySpinner.getSelectedItem().toString();

        try {
            int convertInRub;
            int convertInCur = 0;

            int enteredNumber = Integer.parseInt(firstTextView.getText().toString());

            for (int i = 0; i < currencyList.size(); i++) {
                if (leftChooseCurrency.equals(currencyList.get(i).getCurrencyTicker() + " ("
                        + currencyList.get(i).getCurrencyName() + ")")) {
                    convertInRub = (int) (enteredNumber / Integer.parseInt(currencyList.get(i).getCurrencyNominal()) * Float.parseFloat(currencyList.get(i).getCurrencyValue()));
                    //Log.d("myLOG", convertInRub + "");
                    for (int j = 0; j < currencyList.size(); j++) {
                        if (rightChooseCurrency.equals(currencyList.get(j).getCurrencyTicker() + " ("
                                + currencyList.get(j).getCurrencyName() + ")")) {
                            convertInCur = (int) ((convertInRub * Integer.parseInt(currencyList.get(j).getCurrencyNominal())) / Float.parseFloat(currencyList.get(j).getCurrencyValue()));
                            //Log.d("myLOG", convertInCur + "");
                        }
                    }
                }
                secondTextView.setText(convertInCur + "");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
