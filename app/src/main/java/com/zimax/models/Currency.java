package com.zimax.models;

import java.io.Serializable;

public class Currency implements Serializable {

    private int currencyFlag;
    private String currencyName;
    private String currencyValue;
    private String currencyNominal;
    private String currencyTicker;

    public Currency(String currencyName, String currencyValue, String currencyNominal, String currencyTicker, int currencyFlag) {
        this.currencyName = currencyName;
        this.currencyValue = currencyValue;
        this.currencyNominal = currencyNominal;
        this.currencyTicker = currencyTicker;
        this.currencyFlag = currencyFlag;
    }

    public Currency() {
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getCurrencyValue() {
        return currencyValue;
    }

    public String getCurrencyNominal() {
        return currencyNominal;
    }

    public String getCurrencyTicker() {
        return currencyTicker;
    }

    public int getCurrencyFlag() {
        return currencyFlag;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public void setCurrencyValue(String currencyValue) {
        this.currencyValue = currencyValue;
    }

    public void setCurrencyNominal(String currencyNominal) {
        this.currencyNominal = currencyNominal;
    }

    public void setCurrencyTicker(String currencyTicker) {
        this.currencyTicker = currencyTicker;
    }

    public void setCurrencyFlag(int currencyFlag) {
        this.currencyFlag = currencyFlag;
    }

}