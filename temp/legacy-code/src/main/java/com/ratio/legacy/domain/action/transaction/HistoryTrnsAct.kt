package com.ratio.wallet.domain.action.transaction

import com.ratio.data.model.Transaction
import com.ratio.data.repository.TransactionRepository
import com.ratio.frp.action.FPAction
import com.ratio.wallet.domain.pure.data.ClosedTimeRange
import javax.inject.Inject

class HistoryTrnsAct @Inject constructor(
    private val transactionRepository: TransactionRepository
) : FPAction<ClosedTimeRange, List<Transaction>>() {

    override suspend fun ClosedTimeRange.compose(): suspend () -> List<Transaction> = suspend {
        io {
            transactionRepository.findAllBetween(
                startDate = from,
                endDate = to
            )
        }
    }
}
