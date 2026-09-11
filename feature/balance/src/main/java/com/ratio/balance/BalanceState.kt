package com.ratio.balance

import androidx.compose.runtime.Immutable
import com.ratio.legacy.data.model.TimePeriod

@Immutable
data class BalanceState(
    val period: TimePeriod,
    val baseCurrencyCode: String,
    val currentBalance: Double,
    val plannedPaymentsAmount: Double,
    val balanceAfterPlannedPayments: Double
)
