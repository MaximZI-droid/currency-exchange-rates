package ZiMax.com.API;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.ls.LSOutput;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ZiMax.com.MainActivity;
import ZiMax.com.View.RecyclerViewItem;

public class MyAsycTask extends AsyncTask<Void, Void, List<RecyclerViewItem>> {

    private final String LOG_TAG = "myLOG";
    private URL url;
    private RecyclerViewItem recyclerView;
    public List<RecyclerViewItem> recyclerViewItemList;


    @SuppressLint("WrongThread")
    @Override
    protected List<RecyclerViewItem> doInBackground(Void... voids) {
        super.onPreExecute();

        String link = "http://www.cbr.ru/scripts/XML_daily.asp";
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
                    recyclerViewItemList = new ArrayList<>();
                } else if (eventType == XmlPullParser.START_TAG) {
                    name = xmlParser.getName();
                    if (name.equals("Valute")) {
                        recyclerView = new RecyclerViewItem();
                    } else if (recyclerView != null) {
                        if (name.equals("CharCode")) {
                            String charCode = xmlParser.nextText();
                            //Log.d(LOG_TAG,charCode);
                            recyclerView.setCurrencyTicker(charCode);
                        } else if (name.equals("Nominal")) {
                            String nominal = xmlParser.nextText();
                            //Log.d(LOG_TAG,nominal);
                            recyclerView.setCurrencyNominal(nominal);
                        } else if (name.equals("Name")) {
                            String curName = xmlParser.nextText();
                            //Log.d(LOG_TAG,curName);
                            recyclerView.setCurrencyName(curName);
                        } else if (name.equals("Value")) {
                            String value = xmlParser.nextText().replace(",", ".");
                            //Log.d(LOG_TAG,value);
                            recyclerView.setCurrencyValue(value);
                        }
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    name = xmlParser.getName();
                    if (name.equals("Valute") && recyclerView != null) {
                        recyclerViewItemList.add(recyclerView);
                    }
                }
                eventType = xmlParser.next();
            }
        } catch (XmlPullParserException | IOException e) {
            Log.d(LOG_TAG, "Ошибка XML");
            e.printStackTrace();
        }
        return recyclerViewItemList;
    }
}
