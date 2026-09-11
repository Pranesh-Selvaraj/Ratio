package com.ratio.wallet.domain.action.transaction

import com.ratio.base.legacy.Transaction
import com.ratio.base.legacy.TransactionHistoryItem
import com.ratio.base.time.TimeConverter
import com.ratio.data.db.dao.read.AccountDao
import com.ratio.data.repository.AccountRepository
import com.ratio.data.repository.TagRepository
import com.ratio.frp.action.FPAction
import com.ratio.frp.then
import com.ratio.legacy.datamodel.temp.toLegacyDomain
import com.ratio.legacy.domain.pure.transaction.LegacyTrnDateDividers
import com.ratio.legacy.domain.pure.transaction.transactionsWithDateDividers
import com.ratio.wallet.domain.action.exchange.ExchangeAct
import com.ratio.wallet.domain.action.exchange.actInput
import javax.inject.Inject

class TrnsWithDateDivsAct @Inject constructor(
    private val accountDao: AccountDao,
    private val exchangeAct: ExchangeAct,
    private val tagRepository: TagRepository,
    private val accountRepository: AccountRepository,
) : FPAction<TrnsWithDateDivsAct.Input, List<TransactionHistoryItem>>() {

    override suspend fun Input.compose(): suspend () -> List<TransactionHistoryItem> = suspend {
        transactionsWithDateDividers(
            transactions = transactions,
            baseCurrencyCode = baseCurrency,
            getTags = { tagIds -> tagRepository.findByIds(tagIds) },
            getAccount = accountDao::findById then { it?.toLegacyDomain() },
            accountRepository = accountRepository,
            exchange = ::actInput then exchangeAct
        )
    }

    data class Input(
        val baseCurrency: String,
        val transactions: List<com.ratio.data.model.Transaction>
    )
}

@Deprecated("Uses legacy Transaction")
class LegacyTrnsWithDateDivsAct @Inject constructor(
    private val accountDao: AccountDao,
    private val exchangeAct: ExchangeAct,
    private val timeConverter: TimeConverter,
) : FPAction<LegacyTrnsWithDateDivsAct.Input, List<TransactionHistoryItem>>() {

    override suspend fun Input.compose(): suspend () -> List<TransactionHistoryItem> = suspend {
        LegacyTrnDateDividers.transactionsWithDateDividers(
            transactions = transactions,
            baseCurrencyCode = baseCurrency,

            getAccount = accountDao::findById then { it?.toLegacyDomain() },
            exchange = ::actInput then exchangeAct,
            timeConverter = timeConverter,
        )
    }

    data class Input(
        val baseCurrency: String,
        val transactions: List<Transaction>
    )
}