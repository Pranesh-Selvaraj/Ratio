package com.ratio.design.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ratio.design.l1_buildingBlocks.data.RatioPadding

@Deprecated("Old design system. Use `:ratio-design` and Material3")
fun Modifier.ratioPadding(ratioPadding: RatioPadding): Modifier {
    return this.padding(
        top = ratioPadding.top ?: 0.dp,
        bottom = ratioPadding.bottom ?: 0.dp,
        start = ratioPadding.start ?: 0.dp,
        end = ratioPadding.end ?: 0.dp
    )
}

@Deprecated("Old design system. Use `:ratio-design` and Material3")
fun padding(
    top: Dp? = null,
    start: Dp? = null,
    end: Dp? = null,
    bottom: Dp? = null
): RatioPadding {
    return RatioPadding(
        top = top,
        bottom = bottom,
        start = start,
        end = end
    )
}

@Deprecated("Old design system. Use `:ratio-design` and Material3")
fun padding(
    horizontal: Dp? = null,
    vertical: Dp? = null
): RatioPadding {
    return RatioPadding(
        top = vertical,
        bottom = vertical,
        start = horizontal,
        end = horizontal
    )
}

@Deprecated("Old design system. Use `:ratio-design` and Material3")
fun padding(
    all: Dp? = null
): RatioPadding {
    return RatioPadding(
        top = all,
        bottom = all,
        start = all,
        end = all
    )
}
