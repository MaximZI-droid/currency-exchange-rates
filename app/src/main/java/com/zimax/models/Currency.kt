package com.zimax.models

import java.io.Serializable

class Currency : Serializable {

    var currencyFlag = 0
    var currencyName: String? = null
    var currencyValue: String? = null
    var currencyNominal: String? = null
    var currencyTicker: String? = null

    constructor(currencyName: String?, currencyValue: String?, currencyNominal: String?, currencyTicker: String?, currencyFlag: Int) {
        this.currencyName = currencyName
        this.currencyValue = currencyValue
        this.currencyNominal = currencyNominal
        this.currencyTicker = currencyTicker
        this.currencyFlag = currencyFlag
    }

    constructor() {}
}