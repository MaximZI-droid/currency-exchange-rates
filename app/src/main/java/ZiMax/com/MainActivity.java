package ZiMax.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import ZiMax.com.API.MyAsycTask;
import ZiMax.com.View.RecyclerViewAdapter;
import ZiMax.com.View.RecyclerViewItem;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = "myLOG";
    MyAsycTask myAsycTasK;

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    private RecyclerViewItem recyclerViewItem = new RecyclerViewItem();

    List<RecyclerViewItem> currencyList;

    FloatingActionButton refreshFloatingAB;
    FloatingActionButton convertFloatingAB;

    @Override
    protected void onStart() {
        super.onStart();
        checkInternetConnection();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        myAsycTasK = new MyAsycTask();
        myAsycTasK.execute();
        try {
            myAsycTasK.get();
        } catch (ExecutionException | InterruptedException e) {
            Log.d(LOG_TAG, "Ошибка XML");
            e.printStackTrace();
        }
        recyclerViewItem.createFlags();
        currencyList = myAsycTasK.recyclerViewItemList;

        for (int i = 0; i < currencyList.size(); i++) {
            String str = currencyList.get(i).getCurrencyTicker();
            for (Map.Entry<String, Integer> entry : recyclerViewItem.getFlagContainer().entrySet()) {
                if (entry.getKey().equals(str)) {
                    currencyList.get(i).setCurrencyFlag(entry.getValue());
                }
            }
        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RecyclerViewAdapter(currencyList);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        refreshFloatingAB = findViewById(R.id.refreshFloatingActionButton);

        refreshFloatingAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mStartActivity = new Intent(MainActivity.this, MainActivity.class);
                int mPendingIntentId = 111;
                PendingIntent mPendingIntent = PendingIntent.getActivity(MainActivity.this, mPendingIntentId, mStartActivity,
                        PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager alarmManager = (AlarmManager) MainActivity.this.getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                System.exit(0);
            }
        });

        convertFloatingAB = findViewById(R.id.convertFloatingActionButton);

        convertFloatingAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConverterActivity.class);

                intent.putExtra(ConverterActivity.EXTRA_ITEM, (Serializable) currencyList);

                startActivity(intent);
            }
        });
    }

    private boolean checkInternetConnection() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        if (connectivityManager.getNetworkInfo(0).getState() ==
                android.net.NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(0).getState() ==
                        android.net.NetworkInfo.State.CONNECTING ||
                connectivityManager.getNetworkInfo(1).getState() ==
                        android.net.NetworkInfo.State.CONNECTING ||
                connectivityManager.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {
            Toast.makeText(this, "Интернет подключен", Toast.LENGTH_LONG).show();
            return true;
        } else if (
                connectivityManager.getNetworkInfo(0).getState() ==
                        android.net.NetworkInfo.State.DISCONNECTED ||
                        connectivityManager.getNetworkInfo(1).getState() ==
                                android.net.NetworkInfo.State.DISCONNECTED) {
            Toast.makeText(this, "Нет соединения с интренетом", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }
}
