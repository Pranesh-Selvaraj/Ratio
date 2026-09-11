package com.ratio.onboarding

import androidx.compose.runtime.Immutable
import com.ratio.data.model.Category
import com.ratio.legacy.data.model.AccountBalance
import com.ratio.wallet.domain.data.RatioCurrency
import com.ratio.wallet.domain.deprecated.logic.model.CreateAccountData
import com.ratio.wallet.domain.deprecated.logic.model.CreateCategoryData
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class OnboardingDetailState(
    val currency: RatioCurrency,
    val accounts: ImmutableList<AccountBalance>,
    val accountSuggestions: ImmutableList<CreateAccountData>,
    val categories: ImmutableList<Category>,
    val categorySuggestions: ImmutableList<CreateCategoryData>
)
