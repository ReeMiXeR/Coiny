package com.sandbox.vshcherbakov.coiny.data.model

import com.sandbox.vshcherbakov.coiny.domain.model.CurrencyPair

data class NWCurrencyResponse(
        val RAW: Map<String, Map<String, CurrencyPair>>,
        val DISPLAY: Map<String, Map<String, CurrencyPair>>
)
