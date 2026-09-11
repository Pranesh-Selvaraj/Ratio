package com.ratio.wallet.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ratio.design.l0_system.UI
import com.ratio.legacy.RatioWalletComponentPreview

@Deprecated("Old design system. Use `:ratio-design` and Material3")
@Composable
fun RatioDividerLine(
    modifier: Modifier = Modifier
) {
    Spacer(
        modifier = modifier
            .fillMaxWidth()
            .height(2.dp)
            .background(UI.colors.medium)
    )
}

@Deprecated("Old design system. Use `:ratio-design` and Material3")
@Composable
fun RatioDividerLineRounded(
    modifier: Modifier = Modifier
) {
    Spacer(
        modifier = modifier
            .fillMaxWidth()
            .height(2.dp)
            .background(UI.colors.medium, UI.shapes.rFull)
    )
}

@Preview
@Composable
private fun Preview() {
    RatioWalletComponentPreview {
        RatioDividerLine()
    }
}
