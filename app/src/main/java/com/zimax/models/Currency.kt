package com.zimax.models

import java.io.Serializable

class Currency : Serializable {

    var currencyFlag = 0
    lateinit var currencyName: String
    lateinit var currencyValue: String
    lateinit var currencyNominal: String
    lateinit var currencyTicker: String

    constructor(
        currencyName: String,
        currencyValue: String,
        currencyNominal: String,
        currencyTicker: String,
        currencyFlag: Int
    ) {
        this.currencyName = currencyName
        this.currencyValue = currencyValue
        this.currencyNominal = currencyNominal
        this.currencyTicker = currencyTicker
        this.currencyFlag = currencyFlag
    }

    constructor()
}