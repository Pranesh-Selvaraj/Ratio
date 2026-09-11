package com.ratio.piechart

import androidx.compose.runtime.Immutable
import com.ratio.data.model.Category

@Immutable
data class SelectedCategory(
    val category: Category // null - Unspecified
)
