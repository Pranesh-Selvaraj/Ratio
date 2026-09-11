package com.ratio.wallet.domain.action.loan

import com.ratio.data.db.dao.read.LoanDao
import com.ratio.frp.action.FPAction
import com.ratio.legacy.datamodel.Loan
import com.ratio.legacy.datamodel.temp.toLegacyDomain
import java.util.UUID
import javax.inject.Inject

class LoanByIdAct @Inject constructor(
    private val loanDao: LoanDao
) : FPAction<UUID, Loan?>() {
    override suspend fun UUID.compose(): suspend () -> Loan? = suspend {
        loanDao.findById(this)?.toLegacyDomain()
    }
}
