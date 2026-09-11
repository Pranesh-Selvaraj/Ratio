package com.ratio.legacy.ui.component.transaction

import androidx.compose.runtime.Composable
import com.ratio.data.model.Category
import com.ratio.legacy.datamodel.Account
import com.ratio.legacy.ratioCtx
import java.util.UUID

@Deprecated("Old design system. Use `:ratio-design` and Material3")
@Composable
fun category(
    categoryId: UUID?,
    categories: List<Category>
): Category? {
    val targetId = categoryId ?: return null
    return ratioCtx().categoryMap[targetId] ?: categories.find { it.id.value == targetId }
}

@Deprecated("Old design system. Use `:ratio-design` and Material3")
@Composable
fun account(
    accountId: UUID?,
    accounts: List<Account>
): Account? {
    val targetId = accountId ?: return null
    return ratioCtx().accountMap[targetId] ?: accounts.find { it.id == targetId }
}
