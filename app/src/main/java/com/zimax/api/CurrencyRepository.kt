package com.zimax.api

import android.os.NetworkOnMainThreadException
import android.util.Log
import com.zimax.R
import com.zimax.bussiness.CountryFlag
import com.zimax.models.Currency
import io.reactivex.Single
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL
import java.util.*

class CurrencyRepository {

    val currencyList: Single<List<Currency>>
        get() = Single.create { emitter ->
            val VALUTE = "Valute"
            val CHARCODE = "CharCode"
            val NOMINAL = "Nominal"
            val NAME = "Name"
            val VALUE = "Value"

            lateinit var currencyList: MutableList<Currency>
            lateinit var url: URL
            lateinit var oneCurrency: Currency

            val link = "http://www.cbr.ru/scripts/XML_daily.asp"
            val LOG_TAG = "myLOG"
            try {
                url = URL(link)
            } catch (e: MalformedURLException) {
                Log.d(LOG_TAG, "Некорректный URL адрес $link")
                e.printStackTrace()
            }
            try {
                val factory = XmlPullParserFactory.newInstance()
                factory.isNamespaceAware = true
                val xmlParser = factory.newPullParser()
                xmlParser.setInput(url.openStream(), null)
                var eventType = xmlParser.eventType
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    var name = ""
                    if (eventType == XmlPullParser.START_DOCUMENT) {
                        currencyList = ArrayList()
                    } else if (eventType == XmlPullParser.START_TAG) {
                        name = xmlParser.name
                        if (name == VALUTE) {
                            oneCurrency = Currency()
                        } else {
                            if (name == CHARCODE) {
                                val charCode = xmlParser.nextText()
                                //Log.d(LOG_TAG,charCode);
                                oneCurrency.currencyTicker = charCode
                            } else if (name == NOMINAL) {
                                val nominal = xmlParser.nextText()
                                //Log.d(LOG_TAG,nominal);
                                oneCurrency.currencyNominal = nominal
                            } else if (name == NAME) {
                                val curName = xmlParser.nextText()
                                //Log.d(LOG_TAG,curName);
                                oneCurrency.currencyName = curName
                            } else if (name == VALUE) {
                                val value = xmlParser.nextText().replace(",", ".")
                                //Log.d(LOG_TAG,value);
                                oneCurrency.currencyValue = value
                            }
                        }
                    } else if (eventType == XmlPullParser.END_TAG) {
                        name = xmlParser.name
                        if (name == VALUTE) {
                            currencyList.add(oneCurrency)
                        }
                    }
                    eventType = xmlParser.next()
                }
                currencyList.add(Currency("Российский рубль", "1", "1", "RUB", R.drawable.rus))
                setFlag(currencyList)
                emitter.onSuccess(currencyList)
            } catch (e: XmlPullParserException) {
                Log.d(LOG_TAG, "Ошибка XML")
                e.printStackTrace()
                emitter.onError(e)
            } catch (e: IOException) {
                Log.d(LOG_TAG, "Ошибка XML")
                e.printStackTrace()
                emitter.onError(e)
            } catch (e: NetworkOnMainThreadException) {
                e.printStackTrace()
                emitter.onError(e)
            }
        }

    private fun setFlag(list: List<Currency>) {
        val countryFlag = CountryFlag()
        countryFlag.createFlag()
        for (i in list.indices) {
            val str = list[i].currencyTicker
            for ((key, value) in countryFlag.flagContainer) {
                if (key == str) {
                    list[i].currencyFlag = value
                }
            }
        }
    }
}