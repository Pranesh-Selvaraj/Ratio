package com.ratio.data.model

import com.ratio.data.model.primitive.AssetCode
import com.ratio.data.model.primitive.PositiveDouble

data class ExchangeRate(
    val baseCurrency: AssetCode,
    val currency: AssetCode,
    val rate: PositiveDouble,
    val manualOverride: Boolean,
)
