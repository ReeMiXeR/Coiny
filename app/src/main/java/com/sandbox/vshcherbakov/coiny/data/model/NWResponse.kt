package com.sandbox.vshcherbakov.coiny.data.model

data class NWResponse<T>(
        val Type: Int?,
        val Message: String?,
        val Data: List<T>
)

