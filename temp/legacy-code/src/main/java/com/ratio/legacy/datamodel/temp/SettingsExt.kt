package com.ratio.legacy.datamodel.temp

import com.ratio.data.db.entity.SettingsEntity
import com.ratio.legacy.datamodel.Settings

fun SettingsEntity.toLegacyDomain(): Settings = Settings(
    theme = theme,
    baseCurrency = currency,
    bufferAmount = bufferAmount.toBigDecimal(),
    name = name,
    id = id
)
