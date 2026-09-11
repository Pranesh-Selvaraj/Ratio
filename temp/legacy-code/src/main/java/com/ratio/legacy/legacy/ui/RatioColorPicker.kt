package com.ratio.domain.legacy.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ratio.design.RATIO_COLOR_PICKER_COLORS_FREE
import com.ratio.design.RATIO_COLOR_PICKER_COLORS_PREMIUM
import com.ratio.design.l0_system.UI
import com.ratio.design.l0_system.dynamicContrast
import com.ratio.design.l0_system.style
import com.ratio.design.l1_buildingBlocks.RatioIcon
import com.ratio.design.utils.densityScope
import com.ratio.design.utils.thenIf
import com.ratio.frp.test.TestingContext
import com.ratio.legacy.frp.onScreenStart
import com.ratio.legacy.ratioCtx
import com.ratio.navigation.navigation
import com.ratio.ui.R
import kotlinx.coroutines.launch

@Deprecated("Old design system. Use `:ratio-design` and Material3")
private data class RatioColor(
    val color: Color,
    val premium: Boolean
)

@Deprecated("Old design system. Use `:ratio-design` and Material3")
@Suppress("ParameterNaming")
@Composable
fun ColumnScope.RatioColorPicker(
    selectedColor: Color,
    onColorSelected: (Color) -> Unit
) {
    Text(
        modifier = Modifier.padding(horizontal = 32.dp),
        text = stringResource(R.string.choose_color),
        style = UI.typo.b2.style(
            color = UI.colors.pureInverse,
            fontWeight = FontWeight.ExtraBold
        )
    )

    Spacer(Modifier.height(16.dp))

    val freeRatioColors = RATIO_COLOR_PICKER_COLORS_FREE
        .map {
            RatioColor(
                color = it,
                premium = false
            )
        }

    val premiumRatioColors = RATIO_COLOR_PICKER_COLORS_PREMIUM
        .map {
            RatioColor(
                color = it,
                premium = true
            )
        }

    val ratioColors = freeRatioColors + premiumRatioColors

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    densityScope {
        onScreenStart {
            if (TestingContext.inTest) return@onScreenStart // listState.scrollToItem breaks the tests
            // java.lang.IllegalStateException: pending composition has not been applied

            val selectedColorIndex = ratioColors.indexOfFirst { it.color == selectedColor }
            if (selectedColorIndex != -1) {
                coroutineScope.launch {
                    listState.scrollToItem(
                        index = selectedColorIndex,
                        scrollOffset = 0
                    )
                }
            }
        }
    }

    val ratioContext = ratioCtx()
    val navigation = navigation()

    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        state = listState
    ) {
        items(
            count = ratioColors.size
        ) { index ->
            ColorItem(
                index = index,
                ratioColor = ratioColors[index],
                selectedColor = selectedColor,
                onSelected = {
                    onColorSelected(it.color)
                }
            )
        }
    }
}

@Composable
@Suppress("ParameterNaming")
private fun ColorItem(
    index: Int,
    ratioColor: RatioColor,
    selectedColor: Color,
    onSelected: (RatioColor) -> Unit
) {
    val color = ratioColor.color
    val selected = color == selectedColor

    if (index == 0) {
        Spacer(Modifier.width(24.dp))
    }

    val ratioContext = ratioCtx()
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .size(48.dp)
            .background(color, CircleShape)
            .thenIf(selected) {
                border(width = 4.dp, color = color.dynamicContrast(), CircleShape)
            }
            .clickable(onClick = {
                onSelected(ratioColor)
            })
            .testTag("color_item_${ratioColor.color.value}"),
        contentAlignment = Alignment.Center
    ) {
        if (ratioColor.premium && !ratioContext.isPremium) {
            RatioIcon(
                icon = R.drawable.ic_custom_safe_s,
                tint = color.dynamicContrast()
            )
        }
    }

    Spacer(Modifier.width(if (selected) 16.dp else 24.dp))
}
