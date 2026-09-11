package com.ratio.wallet.domain.action.viewmodel.home

import com.ratio.data.model.Category
import com.ratio.frp.action.FPAction
import com.ratio.legacy.RatioCtx
import javax.inject.Inject

class UpdateCategoriesCacheAct @Inject constructor(
    private val ratioCtx: RatioCtx
) : FPAction<List<Category>, List<Category>>() {
    override suspend fun List<Category>.compose(): suspend () -> List<Category> = suspend {
        val categories = this

        ratioCtx.categoryMap.clear()
        ratioCtx.categoryMap.putAll(categories.map { it.id.value to it })

        categories
    }
}
