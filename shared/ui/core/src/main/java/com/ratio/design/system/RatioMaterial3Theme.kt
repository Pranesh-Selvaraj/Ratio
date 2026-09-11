package com.ratio.design.system

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.ratio.design.system.colors.RatioColors

@Composable
fun RatioMaterial3Theme(
    isTrueBlack: Boolean,
    dark: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (dark) ratioDarkColorScheme(isTrueBlack) else ratioLightColorScheme(),
        content = content,
    )
}

private fun ratioLightColorScheme(): ColorScheme = ColorScheme(
    primary = RatioColors.Purple.primary,
    onPrimary = RatioColors.White,
    primaryContainer = RatioColors.Purple.light,
    onPrimaryContainer = RatioColors.White,
    inversePrimary = RatioColors.Purple.dark,
    secondary = RatioColors.Green.primary,
    onSecondary = RatioColors.White,
    secondaryContainer = RatioColors.Green.light,
    onSecondaryContainer = RatioColors.White,
    tertiary = RatioColors.Green.primary,
    onTertiary = RatioColors.White,
    tertiaryContainer = RatioColors.Green.light,
    onTertiaryContainer = RatioColors.White,

    error = RatioColors.Red.primary,
    onError = RatioColors.White,
    errorContainer = RatioColors.Red.light,
    onErrorContainer = RatioColors.White,

    background = RatioColors.White,
    onBackground = RatioColors.Black,
    surface = RatioColors.White,
    onSurface = RatioColors.Black,
    surfaceVariant = RatioColors.ExtraLightGray,
    onSurfaceVariant = RatioColors.Black,
    surfaceTint = RatioColors.Black,
    inverseSurface = RatioColors.DarkGray,
    inverseOnSurface = RatioColors.White,

    outline = RatioColors.Gray,
    outlineVariant = RatioColors.DarkGray,
    scrim = RatioColors.ExtraDarkGray.copy(alpha = 0.8f)
)

private fun ratioDarkColorScheme(isTrueBlack: Boolean): ColorScheme = ColorScheme(
    primary = RatioColors.Purple.primary,
    onPrimary = RatioColors.White,
    primaryContainer = RatioColors.Purple.light,
    onPrimaryContainer = RatioColors.White,
    inversePrimary = RatioColors.Purple.dark,
    secondary = RatioColors.Green.primary,
    onSecondary = RatioColors.White,
    secondaryContainer = RatioColors.Green.light,
    onSecondaryContainer = RatioColors.White,
    tertiary = RatioColors.Green.primary,
    onTertiary = RatioColors.White,
    tertiaryContainer = RatioColors.Green.light,
    onTertiaryContainer = RatioColors.White,

    error = RatioColors.Red.primary,
    onError = RatioColors.White,
    errorContainer = RatioColors.Red.light,
    onErrorContainer = RatioColors.White,

    background = if (isTrueBlack) RatioColors.TrueBlack else RatioColors.Black,
    onBackground = RatioColors.White,
    surface = if (isTrueBlack) RatioColors.TrueBlack else RatioColors.Black,
    onSurface = RatioColors.White,
    surfaceVariant = RatioColors.ExtraDarkGray,
    onSurfaceVariant = RatioColors.White,
    surfaceTint = RatioColors.White,
    inverseSurface = RatioColors.LightGray,
    inverseOnSurface = if (isTrueBlack) RatioColors.TrueBlack else RatioColors.Black,

    outline = RatioColors.Gray,
    outlineVariant = RatioColors.LightGray,
    scrim = RatioColors.ExtraLightGray.copy(alpha = 0.8f)
)
