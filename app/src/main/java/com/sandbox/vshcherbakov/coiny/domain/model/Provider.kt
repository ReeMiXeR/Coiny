package com.sandbox.vshcherbakov.coiny.domain.model

import com.sandbox.vshcherbakov.coiny.ui.view.adapters.AdapterItem

data class Provider(
        val key: String,
        val name: String,
        val lang: String,
        val img: String
): AdapterItem