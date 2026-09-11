package com.ratio.wallet.domain.action.transaction

import com.ratio.base.legacy.Transaction
import com.ratio.data.model.TransactionId
import com.ratio.data.repository.TransactionRepository
import com.ratio.data.repository.mapper.TransactionMapper
import com.ratio.frp.action.FPAction
import com.ratio.frp.then
import com.ratio.legacy.datamodel.temp.toLegacy
import java.util.UUID
import javax.inject.Inject

class TrnByIdAct @Inject constructor(
    private val transactionRepo: TransactionRepository,
    private val mapper: TransactionMapper
) : FPAction<UUID, Transaction?>() {
    override suspend fun UUID.compose(): suspend () -> Transaction? = suspend {
        this // transactionId
    } then {
        transactionRepo.findById(TransactionId(it))
    } then {
        it?.toLegacy(mapper)
    }
}
