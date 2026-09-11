package com.ratio.legacy.domain.action.settings

import com.ratio.data.db.dao.write.WriteSettingsDao
import com.ratio.frp.action.FPAction
import com.ratio.legacy.datamodel.Settings
import javax.inject.Inject

class UpdateSettingsAct @Inject constructor(
    private val writeSettingsDao: WriteSettingsDao
) : FPAction<Settings, Settings>() {
    override suspend fun Settings.compose(): suspend () -> Settings = suspend {
        writeSettingsDao.save(this.toEntity())
        this
    }
}
