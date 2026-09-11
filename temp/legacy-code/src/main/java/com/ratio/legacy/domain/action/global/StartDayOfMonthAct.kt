package com.ratio.wallet.domain.action.global

import com.ratio.frp.action.FPAction
import com.ratio.frp.then
import com.ratio.legacy.RatioCtx
import com.ratio.base.legacy.SharedPrefs
import javax.inject.Inject

class StartDayOfMonthAct @Inject constructor(
    private val sharedPrefs: SharedPrefs,
    private val ratioCtx: RatioCtx
) : FPAction<Unit, Int>() {

    override suspend fun Unit.compose(): suspend () -> Int = suspend {
        sharedPrefs.getInt(SharedPrefs.START_DATE_OF_MONTH, 1)
    } then { startDay ->
        ratioCtx.setStartDayOfMonth(startDay)
        startDay
    }
}
