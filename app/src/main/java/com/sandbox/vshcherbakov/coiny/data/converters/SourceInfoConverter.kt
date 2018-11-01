package com.sandbox.vshcherbakov.coiny.data.converters

import com.sandbox.vshcherbakov.coiny.domain.model.SourceInfo
import com.sandbox.vshcherbakov.coiny.data.model.NWSourceInfo

fun convertSourceInfo(sourceInfo: NWSourceInfo?): SourceInfo {
    with(requireNotNull(sourceInfo)) {
        return SourceInfo(
                requireNotNull(name),
                requireNotNull(lang),
                requireNotNull(img)
        )
    }
}