package com.ratio.wallet.domain.action.viewmodel.transaction

import com.ratio.base.legacy.Transaction
import com.ratio.data.repository.TransactionRepository
import com.ratio.frp.action.FPAction
import com.ratio.frp.then
import com.ratio.legacy.datamodel.toEntity
import javax.inject.Inject

class SaveTrnLocallyAct @Inject constructor(
    private val transactionRepo: TransactionRepository,
) : FPAction<Transaction, Unit>() {
    override suspend fun Transaction.compose(): suspend () -> Unit = {
        this.copy(
            isSynced = false
        ).toEntity()
    } then {
        transactionRepo::save then {}
    }
}
