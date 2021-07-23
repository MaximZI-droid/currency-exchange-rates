package com.zimax.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.zimax.R
import com.zimax.models.Currency
import com.zimax.view.ConverterActivity
import com.zimax.viewmodel.MainViewModel
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var currencyList: List<Currency>
    private lateinit var adapter: CurrencyRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val converterButton = findViewById<FloatingActionButton>(R.id.convertFloatingActionButton)
        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.setHasFixedSize(true)
        recyclerView.setLayoutManager(LinearLayoutManager(this))


        val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.getDataListCurrency().observe(this, { currencies ->
            adapter = CurrencyRecyclerViewAdapter(currencies)
            recyclerView.setAdapter(adapter)
            currencyList = currencies
        })
        converterButton.setOnClickListener { openConverter() }
    }

    private fun openConverter() {
        val intent = Intent(this@MainActivity, ConverterActivity::class.java)
        intent.putExtra(ConverterActivity.EXTRA_ITEM, currencyList as Serializable?)
        startActivity(intent)
    }
}