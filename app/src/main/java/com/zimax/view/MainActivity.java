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
import java.util.List;

import com.zimax.R;
import com.zimax.api.CurrencyRepository;
import com.zimax.models.Currency;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "myLOG";
    private Disposable disposableCurrencyRepository;
    private RecyclerView recyclerView;
    private final CurrencyRepository currencyRepository = new CurrencyRepository();
    private List<Currency> currencyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton converterButton = findViewById(R.id.convertFloatingActionButton);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        converterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConverterActivity.class);
                intent.putExtra(ConverterActivity.EXTRA_ITEM, (Serializable) currencyList);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getCurrencyList();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (disposableCurrencyRepository != null) {
            disposableCurrencyRepository.dispose();
        }
    }

    private void getCurrencyList() {
        
         currencyRepository
                .getCurrencyList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Currency>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposableCurrencyRepository = d;
                    }

                    @Override
                    public void onSuccess(@NonNull List<Currency> currencies) {
                        CurrencyRecyclerViewAdapter adapter = new CurrencyRecyclerViewAdapter(currencies);
                        recyclerView.setAdapter(adapter);
                        currencyList = currencies;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(LOG_TAG, "onError " + e);
                    }
                });
    }
}
