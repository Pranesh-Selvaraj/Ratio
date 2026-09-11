package com.ratio.wallet.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ratio.design.l0_system.UI
import com.ratio.legacy.RatioWalletComponentPreview

@Deprecated("Old design system. Use `:ratio-design` and Material3")
@Composable
fun RatioDividerDot() {
    Spacer(
        modifier = Modifier
            .size(4.dp)
            .background(UI.colors.mediumInverse, CircleShape)
    )
}

@Preview
@Composable
private fun Preview() {
    RatioWalletComponentPreview {
        RatioDividerDot()
    }
}
