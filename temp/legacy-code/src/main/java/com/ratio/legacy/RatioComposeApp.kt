package com.ratio.legacy

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import com.ratio.base.legacy.Theme
import com.ratio.base.legacy.appContext
import com.ratio.design.RatioContext
import com.ratio.design.api.RatioDesign
import com.ratio.design.api.ratioContext
import com.ratio.design.api.systems.RatioWalletDesign
import com.ratio.design.l0_system.UI
import com.ratio.design.utils.RatioPreview
import com.ratio.domain.RootScreen
import com.ratio.navigation.Navigation
import com.ratio.navigation.NavigationRoot

@Deprecated("Old design system. Use `:ratio-design` and Material3")
@Composable
fun ratioCtx(): RatioCtx = ratioContext() as RatioCtx

@Deprecated("Old design system. Use `:ratio-design` and Material3")
@Composable
fun rootView(): View = LocalView.current

@Deprecated("Old design system. Use `:ratio-design` and Material3")
@Composable
fun rootActivity(): AppCompatActivity = LocalContext.current as AppCompatActivity

@Composable
fun rootScreen(): RootScreen = LocalContext.current as RootScreen

@Deprecated("Old design system. Use `:ratio-design` and Material3")
@Composable
fun RatioWalletComponentPreview(
    theme: Theme = Theme.LIGHT,
    Content: @Composable BoxScope.() -> Unit
) {
    RatioWalletPreview(
        theme = theme
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(UI.colors.pure),
            contentAlignment = Alignment.Center
        ) {
            Content()
        }
    }
}

@Deprecated("Old design system. Use `:ratio-design` and Material3")
@Composable
fun RatioWalletPreview(
    theme: Theme = Theme.LIGHT,
    content: @Composable BoxWithConstraintsScope.() -> Unit
) {
    appContext = rootView().context
    RatioPreview(
        theme = theme,
        design = appDesign(RatioCtx()),
    ) {
        NavigationRoot(navigation = Navigation()) {
            content()
        }
    }
}

@Deprecated("Old design system. Use `:ratio-design` and Material3")
fun appDesign(context: RatioCtx): RatioWalletDesign = object : RatioWalletDesign() {
    override fun context(): RatioContext = context
}
