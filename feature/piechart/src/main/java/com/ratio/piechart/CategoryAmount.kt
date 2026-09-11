package com.ratio.piechart

import androidx.compose.runtime.Immutable
import com.ratio.base.legacy.Transaction
import com.ratio.data.model.Category

@Immutable
data class CategoryAmount(
    val category: Category?,
    val amount: Double,
    val associatedTransactions: List<Transaction> = emptyList(),
    val isCategoryUnspecified: Boolean = false
)
