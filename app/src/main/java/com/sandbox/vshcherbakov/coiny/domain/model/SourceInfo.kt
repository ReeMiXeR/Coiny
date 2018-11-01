package com.sandbox.vshcherbakov.coiny.domain.model

import java.io.Serializable

data class SourceInfo(
        val name: String,
        val lang: String,
        val img: String
): Serializable