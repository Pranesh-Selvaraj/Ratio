package com.ratio.legacy.datamodel.temp

import com.ratio.data.db.entity.ExchangeRateEntity
import com.ratio.legacy.datamodel.ExchangeRate

fun ExchangeRateEntity.toLegacyDomain(): ExchangeRate = ExchangeRate(
    baseCurrency = baseCurrency,
    currency = currency,
    rate = rate
)
