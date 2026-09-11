package com.ratio.design.l0_system

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.ratio.base.legacy.Theme
import com.ratio.design.api.RatioDesign
import com.ratio.design.system.RatioMaterial3Theme

@Deprecated("Old design system. Use `:ratio-design` and Material3")
val LocalRatioColors = compositionLocalOf<RatioColors> { error("No RatioColors") }

@Deprecated("Old design system. Use `:ratio-design` and Material3")
val LocalRatioTypography = compositionLocalOf<RatioTypography> { error("No RatioTypography") }

@Deprecated("Old design system. Use `:ratio-design` and Material3")
val LocalRatioShapes = compositionLocalOf<RatioShapes> { error("No RatioShapes") }

@Deprecated("Old design system. Use `:ratio-design` and Material3")
object UI {
    val colors: RatioColors
        @Composable
        @ReadOnlyComposable
        get() = LocalRatioColors.current

    val typo: RatioTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalRatioTypography.current

    val shapes: RatioShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalRatioShapes.current
}

@Deprecated("Old design system. Use `:ratio-design` and Material3")
@Composable
fun RatioTheme(
    theme: Theme,
    design: RatioDesign,
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = design.colors(theme, isDarkTheme)
    val typography = design.typography()
    val shapes = design.shapes()

    CompositionLocalProvider(
        LocalRatioColors provides colors,
        LocalRatioTypography provides typography,
        LocalRatioShapes provides shapes
    ) {
        val view = LocalView.current
        if (!view.isInEditMode && view.context is Activity) {
            SideEffect {
                val window = (view.context as Activity).window
                window.statusBarColor = Color.Transparent.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                    colors.isLight
            }
        }

        RatioMaterial3Theme(
            dark = !colors.isLight,
            isTrueBlack = theme == Theme.AMOLED_DARK,
            content = content,
        )
    }
}
