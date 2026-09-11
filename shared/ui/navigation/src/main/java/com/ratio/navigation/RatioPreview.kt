package com.ratio.navigation

import androidx.compose.runtime.Composable
import com.ratio.design.system.RatioMaterial3Theme

@Composable
fun RatioPreview(
    dark: Boolean = false,
    content: @Composable () -> Unit,
) {
    NavigationRoot(navigation = Navigation()) {
        RatioMaterial3Theme(dark = dark, isTrueBlack = false, content = content)
    }
}
