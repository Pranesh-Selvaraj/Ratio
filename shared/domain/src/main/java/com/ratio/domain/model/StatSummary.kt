package com.ratio.domain.model

import com.ratio.data.model.primitive.AssetCode
import com.ratio.data.model.primitive.NonNegativeInt
import com.ratio.data.model.primitive.PositiveDouble

data class StatSummary(
    val trnCount: NonNegativeInt,
    val values: Map<AssetCode, PositiveDouble>,
) {
    companion object {
        val Zero = StatSummary(
            values = emptyMap(),
            trnCount = NonNegativeInt.Zero
        )
    }
}