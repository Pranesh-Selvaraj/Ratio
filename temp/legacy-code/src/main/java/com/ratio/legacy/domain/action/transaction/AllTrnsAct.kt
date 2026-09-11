package com.ratio.wallet.domain.action.transaction

import com.ratio.data.model.Transaction
import com.ratio.data.repository.TransactionRepository
import com.ratio.frp.action.FPAction
import javax.inject.Inject

class AllTrnsAct @Inject constructor(
    private val transactionRepository: TransactionRepository
) : FPAction<Unit, List<Transaction>>() {
    override suspend fun Unit.compose(): suspend () -> List<Transaction> = suspend {
        transactionRepository.findAll()
    }
}
