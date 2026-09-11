package com.ratio.wallet.domain.pure.data

import com.ratio.base.time.TimeProvider
import com.ratio.legacy.utils.ratioMinTime
import java.time.Instant

data class ClosedTimeRange(
    val from: Instant,
    val to: Instant,
) {
    companion object {
        fun allTimeRatio(
            timeProvider: TimeProvider,
        ): ClosedTimeRange = ClosedTimeRange(
            from = ratioMinTime(),
            to = timeProvider.utcNow(),
        )

        fun to(to: Instant): ClosedTimeRange = ClosedTimeRange(
            from = ratioMinTime(),
            to = to
        )
    }
}
