package com.zimax.bussiness;

import com.zimax.R;
import java.util.HashMap;
import java.util.Map;

public class CountryFlag {

    private Map<String, Integer> mapFlagContainer = new HashMap<>();

    public Map<String, Integer> getFlagContainer() {
        return mapFlagContainer;
    }

    public void createFlag() {
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
