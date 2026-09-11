package com.ratio.wallet.domain.action.settings

import com.ratio.data.db.dao.read.SettingsDao
import com.ratio.frp.action.FPAction
import javax.inject.Inject

class BaseCurrencyAct @Inject constructor(
    private val settingsDao: SettingsDao
) : FPAction<Unit, String>() {
    override suspend fun Unit.compose(): suspend () -> String = suspend {
        io { settingsDao.findFirst().currency }
    }
}
