package com.ratio.legacy.data.model

import androidx.compose.runtime.Immutable
import com.ratio.base.time.TimeProvider
import com.ratio.data.model.IntervalType
import com.ratio.legacy.forDisplay
import com.ratio.legacy.incrementDate
import java.time.Instant

@Suppress("DataClassFunctions")
@Immutable
data class LastNTimeRange(
    val periodN: Int,
    val periodType: IntervalType,
) {
    fun fromDate(
        timeProvider: TimeProvider
    ): Instant = periodType.incrementDate(
        date = timeProvider.utcNow(),
        intervalN = -periodN.toLong()
    )

    fun forDisplay(): String =
        "$periodN ${periodType.forDisplay(periodN)}"
}
