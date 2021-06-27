package ZiMax.com.View;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ZiMax.com.API.MyAsycTask;
import ZiMax.com.R;

public class RecyclerViewItem implements Serializable {

    private int currencyFlag;
    private String currencyName;
    private String currencyValue;
    private String currencyNominal;
    private String currencyTicker;

    public Map<String, Integer> mapFlagContainer = new HashMap<>();

    public RecyclerViewItem(String currencyName, String currencyValue, String currencyNominal, String currencyTicker, int currencyFlag) {
        this.currencyName = currencyName;
        this.currencyValue = currencyValue;
        this.currencyNominal = currencyNominal;
        this.currencyTicker = currencyTicker;
        this.currencyFlag = currencyFlag;
    }

    public RecyclerViewItem() {
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

    public Map<String, Integer> getFlagContainer() {
        return mapFlagContainer;
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

    public void createFlags() {
        mapFlagContainer.put("AUD", R.drawable.aud);
        mapFlagContainer.put("AMD", R.drawable.amd);
        mapFlagContainer.put("BGN", R.drawable.bgn);
        mapFlagContainer.put("AZN", R.drawable.azn);
        mapFlagContainer.put("BRL", R.drawable.brl);
        mapFlagContainer.put("BYN", R.drawable.byn);
        mapFlagContainer.put("CAD", R.drawable.cad);
        mapFlagContainer.put("CHF", R.drawable.chf);
        mapFlagContainer.put("CNY", R.drawable.cny);
        mapFlagContainer.put("CZK", R.drawable.czk);
        mapFlagContainer.put("DKK", R.drawable.dkk);
        mapFlagContainer.put("EUR", R.drawable.eur);
        mapFlagContainer.put("FBP", R.drawable.gbp);
        mapFlagContainer.put("HKD", R.drawable.hkd);
        mapFlagContainer.put("HUF", R.drawable.huf);
        mapFlagContainer.put("INR", R.drawable.inr);
        mapFlagContainer.put("JPY", R.drawable.jpy);
        mapFlagContainer.put("KGS", R.drawable.kgs);
        mapFlagContainer.put("KRW", R.drawable.krw);
        mapFlagContainer.put("KZT", R.drawable.kzt);
        mapFlagContainer.put("MDL", R.drawable.mdl);
        mapFlagContainer.put("NOK", R.drawable.nok);
        mapFlagContainer.put("RON", R.drawable.ron);
        mapFlagContainer.put("SEK", R.drawable.sek);
        mapFlagContainer.put("SGD", R.drawable.sgd);
        mapFlagContainer.put("TJS", R.drawable.tjs);
        mapFlagContainer.put("TMT", R.drawable.tmt);
        mapFlagContainer.put("TRY", R.drawable.try_f);
        mapFlagContainer.put("UAH", R.drawable.uah);
        mapFlagContainer.put("USD", R.drawable.usd);
        mapFlagContainer.put("UZS", R.drawable.uzs);
        mapFlagContainer.put("XDR", R.drawable.xdr);
        mapFlagContainer.put("ZAR", R.drawable.zar);
        mapFlagContainer.put("GBP", R.drawable.gbp_fl);
        mapFlagContainer.put("PLN", R.drawable.pln_fl);
    }
}
