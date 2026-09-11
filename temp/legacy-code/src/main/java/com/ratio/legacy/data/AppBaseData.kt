package com.ratio.legacy.data

import androidx.compose.runtime.Immutable
import com.ratio.data.model.Category
import com.ratio.legacy.datamodel.Account
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class AppBaseData(
    val baseCurrency: String,
    val accounts: ImmutableList<Account>,
    val categories: ImmutableList<Category>
)
