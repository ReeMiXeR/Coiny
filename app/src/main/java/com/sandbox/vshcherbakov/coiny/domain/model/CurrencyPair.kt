package com.sandbox.vshcherbakov.coiny.domain.model

import com.sandbox.vshcherbakov.coiny.ui.view.adapters.AdapterItem

data class CurrencyPair(
        val CHANGE24HOUR: String,
        val CHANGEDAY: String,
        val CHANGEPCT24HOUR: String,
        val CHANGEPCTDAY: String,
        val FROMSYMBOL: String,
        val HIGH24HOUR: String,
        val HIGHDAY: String,
        val LASTMARKET: String,
        val LASTTRADEID: String,
        val LASTUPDATE: String,
        val LASTVOLUME: String,
        val LASTVOLUMETO: String,
        val LOW24HOUR: String,
        val LOWDAY: String,
        val MARKET: String,
        val MKTCAP: String,
        val OPEN24HOUR: String,
        val OPENDAY: String,
        val PRICE: String,
        val SUPPLY: String,
        val TOSYMBOL: String,
        val TOTALVOLUME24H: String,
        val TOTALVOLUME24HTO: String,
        val VOLUME24HOUR: String,
        val VOLUME24HOURTO: String,
        val VOLUMEDAY: String,
        val VOLUMEDAYTO: String
): AdapterItem