package com.ratio.design.l2_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ratio.design.l0_system.UI
import com.ratio.design.l0_system.White
import com.ratio.design.l0_system.colorAs
import com.ratio.design.l0_system.style
import com.ratio.design.l1_buildingBlocks.data.Background
import com.ratio.design.l1_buildingBlocks.data.background
import com.ratio.design.l1_buildingBlocks.data.clipBackground
import com.ratio.design.utils.RatioComponentPreview
import com.ratio.design.utils.padding

@Deprecated("Old design system. Use `:ratio-design` and Material3")
@Composable
fun Button(
    modifier: Modifier = Modifier,
    text: String,
    background: Background = Background.Solid(
        color = UI.colors.primary,
        shape = UI.shapes.rFull,
        padding = padding(
            horizontal = 24.dp,
            vertical = 12.dp
        )
    ),
    textStyle: TextStyle = UI.typo.b2.style(
        color = White,
        textAlign = TextAlign.Center
    ),
    onClick: () -> Unit
) {
    Text(
        modifier = modifier
            .clipBackground(background)
            .clickable(
                onClick = onClick
            )
            .background(background),
        text = text,
        style = textStyle
    )
}

@Preview
@Composable
private fun Preview_Solid() {
    RatioComponentPreview {
        Button(
            text = "Okay",
            background = Background.Solid(
                color = UI.colors.primary,
                shape = UI.shapes.rFull,
                padding = padding(
                    horizontal = 24.dp,
                    vertical = 12.dp
                )
            ),
            textStyle = UI.typo.b1.colorAs(White)
        ) {
        }
    }
}

@Preview
@Composable
private fun Preview_Outlined() {
    RatioComponentPreview {
        Button(
            text = "Continue",
            background = Background.Outlined(
                color = UI.colors.pureInverse,
                width = 1.dp,
                shape = UI.shapes.rFull,
                padding = padding(
                    horizontal = 24.dp,
                    vertical = 12.dp
                )
            ),
            textStyle = UI.typo.b1.colorAs(UI.colors.pureInverse)
        ) {
        }
    }
}

@Preview
@Composable
private fun Preview_FillMaxWidth() {
    RatioComponentPreview {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = "Add task"
        ) {
        }
    }
}
