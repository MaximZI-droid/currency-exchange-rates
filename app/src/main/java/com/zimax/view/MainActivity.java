package com.zimax.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.List;

import com.zimax.viewmodel.MainViewModel;
import com.zimax.R;
import com.zimax.models.Currency;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Currency> currencyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton converterButton = findViewById(R.id.convertFloatingActionButton);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.dataListCurrency.observe(this, new Observer<List<Currency>>() {
            @Override
            public void onChanged(List<Currency> currencies) {
                CurrencyRecyclerViewAdapter adapter = new CurrencyRecyclerViewAdapter(currencies);
                recyclerView.setAdapter(adapter);
                currencyList = currencies;
            }
        });

        converterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConverterActivity.class);
                intent.putExtra(ConverterActivity.EXTRA_ITEM, (Serializable) currencyList);
                startActivity(intent);
            }
        });
    }
}
