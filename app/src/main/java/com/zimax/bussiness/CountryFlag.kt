package com.zimax.bussiness

import com.zimax.R
import java.util.*

class CountryFlag {

    private val mapFlagContainer: MutableMap<String, Int> = HashMap()
    val flagContainer: Map<String, Int>
        get() = mapFlagContainer

    fun createFlag() {
        mapFlagContainer["AUD"] = R.drawable.aud
        mapFlagContainer["AMD"] = R.drawable.amd
        mapFlagContainer["BGN"] = R.drawable.bgn
        mapFlagContainer["AZN"] = R.drawable.azn
        mapFlagContainer["BRL"] = R.drawable.brl
        mapFlagContainer["BYN"] = R.drawable.byn
        mapFlagContainer["CAD"] = R.drawable.cad
        mapFlagContainer["CHF"] = R.drawable.chf
        mapFlagContainer["CNY"] = R.drawable.cny
        mapFlagContainer["CZK"] = R.drawable.czk
        mapFlagContainer["DKK"] = R.drawable.dkk
        mapFlagContainer["EUR"] = R.drawable.eur
        mapFlagContainer["FBP"] = R.drawable.gbp
        mapFlagContainer["HKD"] = R.drawable.hkd
        mapFlagContainer["HUF"] = R.drawable.huf
        mapFlagContainer["INR"] = R.drawable.inr
        mapFlagContainer["JPY"] = R.drawable.jpy
        mapFlagContainer["KGS"] = R.drawable.kgs
        mapFlagContainer["KRW"] = R.drawable.krw
        mapFlagContainer["KZT"] = R.drawable.kzt
        mapFlagContainer["MDL"] = R.drawable.mdl
        mapFlagContainer["NOK"] = R.drawable.nok
        mapFlagContainer["RON"] = R.drawable.ron
        mapFlagContainer["SEK"] = R.drawable.sek
        mapFlagContainer["SGD"] = R.drawable.sgd
        mapFlagContainer["TJS"] = R.drawable.tjs
        mapFlagContainer["TMT"] = R.drawable.tmt
        mapFlagContainer["TRY"] = R.drawable.try_f
        mapFlagContainer["UAH"] = R.drawable.uah
        mapFlagContainer["USD"] = R.drawable.usd
        mapFlagContainer["UZS"] = R.drawable.uzs
        mapFlagContainer["XDR"] = R.drawable.xdr
        mapFlagContainer["ZAR"] = R.drawable.zar
        mapFlagContainer["GBP"] = R.drawable.gbp_fl
        mapFlagContainer["PLN"] = R.drawable.pln_fl
    }
}