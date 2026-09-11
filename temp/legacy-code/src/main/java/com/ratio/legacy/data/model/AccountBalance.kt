package com.ratio.legacy.data.model

import androidx.compose.runtime.Immutable
import com.ratio.legacy.datamodel.Account

@Immutable
data class AccountBalance(
    val account: Account,
    val balance: Double
)
