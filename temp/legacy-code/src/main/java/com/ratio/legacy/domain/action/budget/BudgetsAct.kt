package com.ratio.wallet.domain.action.budget

import com.ratio.data.db.dao.read.BudgetDao
import com.ratio.frp.action.FPAction
import com.ratio.frp.action.thenMap
import com.ratio.frp.then
import com.ratio.legacy.datamodel.Budget
import com.ratio.legacy.datamodel.temp.toLegacyDomain
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import javax.inject.Inject

class BudgetsAct @Inject constructor(
    private val budgetDao: BudgetDao
) : FPAction<Unit, ImmutableList<Budget>>() {
    override suspend fun Unit.compose(): suspend () -> ImmutableList<Budget> = suspend {
        budgetDao.findAll()
    } thenMap { it.toLegacyDomain() } then { it.toImmutableList() }
}
