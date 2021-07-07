package com.zimax.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;

import com.zimax.R;
import com.zimax.api.GetCurrencyAsyncTask;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "myLOG";
    private GetCurrencyAsyncTask getCurrencyAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton converterButton = findViewById(R.id.convertFloatingActionButton);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        getCurrencyList();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CurrencyRecyclerViewAdapter adapter = new CurrencyRecyclerViewAdapter(getCurrencyAsyncTask.getCurrencyList());
        recyclerView.setAdapter(adapter);

        converterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConverterActivity.class);
                intent.putExtra(ConverterActivity.EXTRA_ITEM, (Serializable) getCurrencyAsyncTask.getCurrencyList());
                startActivity(intent);
            }
        });
    }

    private void getCurrencyList() {
        getCurrencyAsyncTask = new GetCurrencyAsyncTask();
        getCurrencyAsyncTask.execute();
        try {
            getCurrencyAsyncTask.get();
        } catch (ExecutionException | InterruptedException e) {
            Log.d(LOG_TAG, "Ошибка XML");
            e.printStackTrace();
        }
    }
}
