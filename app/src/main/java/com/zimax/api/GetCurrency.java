package com.zimax.api;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zimax.R;
import com.zimax.view.CountryFlag;
import com.zimax.view.Currency;

public class GetCurrency extends AsyncTask<Void, Void, List<Currency>> {

    private URL url;
    private Currency oneCurrency;
    public List<Currency> currencyList;


    @SuppressLint("WrongThread")
    @Override
    protected List<Currency> doInBackground(Void... voids) {
        super.onPreExecute();

        String link = "http://www.cbr.ru/scripts/XML_daily.asp";
        String LOG_TAG = "myLOG";

        final String VALUTE = "Valute";
        final String CHARCODE = "CharCode";
        final String NOMINAL = "Nominal";
        final String NAME = "Name";
        final String VALUE = "Value";


        try {
            url = new URL(link);
        } catch (MalformedURLException e) {
            Log.d(LOG_TAG, "Некорректный URL адрес " + link);
            e.printStackTrace();
        }
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xmlParser = factory.newPullParser();
            xmlParser.setInput(url.openStream(), null);

            int eventType = xmlParser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String name = "";
                if (eventType == XmlPullParser.START_DOCUMENT) {
                    currencyList = new ArrayList<>();
                } else if (eventType == XmlPullParser.START_TAG) {
                    name = xmlParser.getName();
                    if (name.equals(VALUTE)) {
                        oneCurrency = new Currency();
                    } else if (oneCurrency != null) {
                        if (name.equals(CHARCODE)) {
                            String charCode = xmlParser.nextText();
                            //Log.d(LOG_TAG,charCode);
                            oneCurrency.setCurrencyTicker(charCode);
                        } else if (name.equals(NOMINAL)) {
                            String nominal = xmlParser.nextText();
                            //Log.d(LOG_TAG,nominal);
                            oneCurrency.setCurrencyNominal(nominal);
                        } else if (name.equals(NAME)) {
                            String curName = xmlParser.nextText();
                            //Log.d(LOG_TAG,curName);
                            oneCurrency.setCurrencyName(curName);
                        } else if (name.equals(VALUE)) {
                            String value = xmlParser.nextText().replace(",", ".");
                            //Log.d(LOG_TAG,value);
                            oneCurrency.setCurrencyValue(value);
                        }
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    name = xmlParser.getName();
                    if (name.equals(VALUTE) && oneCurrency != null) {
                        currencyList.add(oneCurrency);
                    }
                }
                eventType = xmlParser.next();
            }
        } catch (XmlPullParserException e) {
            Log.d(LOG_TAG, "Ошибка XML");
            e.printStackTrace();
        } catch (IOException e){
            Log.d(LOG_TAG, "Ошибка подключения интернета");
            e.printStackTrace();
        }
        currencyList.add(new Currency("Российский рубль", "1", "1", "RUB", R.drawable.rus));
        setFlag(currencyList);
        return currencyList;
    }

    private void setFlag(List<Currency> list){
        CountryFlag countryFlag = new CountryFlag();
        countryFlag.createFlag();

        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i).getCurrencyTicker();
            for (Map.Entry<String, Integer> entry : countryFlag.getFlagContainer().entrySet()) {
                if (entry.getKey().equals(str)) {
                    list.get(i).setCurrencyFlag(entry.getValue());
                }
            }
        }
    }
}
