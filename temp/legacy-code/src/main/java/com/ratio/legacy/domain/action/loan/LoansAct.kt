package com.ratio.wallet.domain.action.loan

import com.ratio.data.db.dao.read.LoanDao
import com.ratio.frp.action.FPAction
import com.ratio.frp.action.thenMap
import com.ratio.frp.then
import com.ratio.legacy.datamodel.Loan
import com.ratio.legacy.datamodel.temp.toLegacyDomain
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import javax.inject.Inject

class LoansAct @Inject constructor(
    private val loanDao: LoanDao
) : FPAction<Unit, ImmutableList<Loan>>() {
    override suspend fun Unit.compose(): suspend () -> ImmutableList<Loan> = suspend {
        loanDao.findAll()
    } thenMap { it.toLegacyDomain() } then { it.toImmutableList() }
}
