package com.ratio.home

import androidx.compose.runtime.Immutable
import com.ratio.base.legacy.Theme
import com.ratio.base.legacy.TransactionHistoryItem
import com.ratio.home.customerjourney.CustomerJourneyCardModel
import com.ratio.legacy.data.AppBaseData
import com.ratio.legacy.data.BufferInfo
import com.ratio.legacy.data.LegacyDueSection
import com.ratio.legacy.data.model.TimePeriod
import com.ratio.wallet.domain.pure.data.IncomeExpensePair
import kotlinx.collections.immutable.ImmutableList
import java.math.BigDecimal

@Immutable
data class HomeState(
    val theme: Theme,
    val name: String,

    val period: TimePeriod,
    val baseData: AppBaseData,

    val history: ImmutableList<TransactionHistoryItem>,
    val stats: IncomeExpensePair,

    val balance: BigDecimal,

    val buffer: BufferInfo,

    val upcoming: LegacyDueSection,
    val overdue: LegacyDueSection,

    val customerJourneyCards: ImmutableList<CustomerJourneyCardModel>,
    val hideBalance: Boolean,
    val hideIncome: Boolean,
    val expanded: Boolean,
    val shouldShowAccountSpecificColorInTransactions: Boolean
)
