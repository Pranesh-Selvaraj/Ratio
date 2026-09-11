package com.ratio.search

import com.ratio.base.legacy.TransactionHistoryItem
import com.ratio.data.model.Category
import com.ratio.legacy.datamodel.Account
import kotlinx.collections.immutable.ImmutableList

data class SearchState(
    val searchQuery: String,
    val transactions: ImmutableList<TransactionHistoryItem>,
    val baseCurrency: String,
    val accounts: ImmutableList<Account>,
    val categories: ImmutableList<Category>,
    val shouldShowAccountSpecificColorInTransactions: Boolean
)
