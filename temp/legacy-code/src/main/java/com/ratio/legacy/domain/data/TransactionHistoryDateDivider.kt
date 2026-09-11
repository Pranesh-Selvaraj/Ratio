package com.ratio.wallet.domain.data

import androidx.compose.runtime.Immutable
import com.ratio.base.legacy.TransactionHistoryItem
import java.time.LocalDate

@Immutable
data class TransactionHistoryDateDivider(
    val date: LocalDate,
    val income: Double,
    val expenses: Double
) : TransactionHistoryItem
