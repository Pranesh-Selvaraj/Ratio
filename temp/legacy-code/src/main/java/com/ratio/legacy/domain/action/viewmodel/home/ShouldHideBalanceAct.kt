package com.ratio.wallet.domain.action.viewmodel.home

import com.ratio.frp.action.FPAction
import com.ratio.base.legacy.SharedPrefs
import javax.inject.Inject

class ShouldHideBalanceAct @Inject constructor(
    private val sharedPrefs: SharedPrefs
) : FPAction<Unit, Boolean>() {
    override suspend fun Unit.compose(): suspend () -> Boolean = {
        sharedPrefs.getBoolean(
            SharedPrefs.HIDE_CURRENT_BALANCE,
            false
        )
    }
}
