package com.ratio.wallet.domain.action.transaction

import com.ratio.base.legacy.Transaction
import com.ratio.data.db.dao.read.TransactionDao
import com.ratio.frp.action.FPAction
import com.ratio.frp.action.thenFilter
import com.ratio.legacy.datamodel.temp.toLegacyDomain
import java.util.UUID
import javax.inject.Inject

class TrnsWithRangeAndAccFiltersAct @Inject constructor(
    private val transactionDao: TransactionDao
) : FPAction<TrnsWithRangeAndAccFiltersAct.Input, List<Transaction>>() {

    override suspend fun Input.compose(): suspend () -> List<Transaction> = suspend {
        transactionDao.findAllBetween(range.from(), range.to())
            .map { it.toLegacyDomain() }
    } thenFilter {
        accountIdFilterSet.contains(it.accountId) || accountIdFilterSet.contains(it.toAccountId)
    }

    data class Input(
        val range: com.ratio.legacy.data.model.FromToTimeRange,
        val accountIdFilterSet: Set<UUID>
    )
}
