package com.zimax.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zimax.R
import com.zimax.domain.models.Currency
import com.zimax.presentation.view.CurrencyRecyclerViewAdapter.RecyclerViewViewHolder

class CurrencyRecyclerViewAdapter(private val currencyList: List<Currency>) :
    RecyclerView.Adapter<RecyclerViewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return RecyclerViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        val currency = currencyList[position]
        holder.imageView.setImageResource(currency.currencyFlag)
        val str = """${currency.currencyName} (${currency.currencyTicker})
цена : ${currency.currencyValue} RUB"""
        holder.textView1.text = str
    }

    override fun getItemCount(): Int {
        return currencyList.size
    }

    class RecyclerViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView
        var textView1: TextView

        init {
            imageView = itemView.findViewById(R.id.imageViewInCard)
            textView1 = itemView.findViewById(R.id.textViewInCard)
        }
    }
}