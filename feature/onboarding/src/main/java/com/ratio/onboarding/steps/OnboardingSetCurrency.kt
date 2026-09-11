package com.ratio.onboarding.steps

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ratio.design.l0_system.UI
import com.ratio.design.l0_system.style
import com.ratio.legacy.RatioWalletPreview
import com.ratio.legacy.utils.setStatusBarDarkTextCompat
import com.ratio.navigation.navigation
import com.ratio.ui.R
import com.ratio.wallet.domain.data.RatioCurrency
import com.ratio.wallet.ui.theme.GradientRatio
import com.ratio.wallet.ui.theme.White
import com.ratio.wallet.ui.theme.components.BackButton
import com.ratio.wallet.ui.theme.components.CurrencyPicker
import com.ratio.wallet.ui.theme.components.GradientCutBottom
import com.ratio.wallet.ui.theme.components.OnboardingButton

@Composable
fun BoxWithConstraintsScope.OnboardingSetCurrency(
    preselectedCurrency: RatioCurrency,
    onSetCurrency: (RatioCurrency) -> Unit
) {
    setStatusBarDarkTextCompat(darkText = UI.colors.isLight)

    var currency by remember { mutableStateOf(preselectedCurrency) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
    ) {
        Spacer(Modifier.height(16.dp))

        var keyboardVisible by remember {
            mutableStateOf(false)
        }

        val nav = navigation()
        BackButton(
            modifier = Modifier.padding(start = 20.dp)
        ) {
            nav.onBackPressed()
        }

        if (!keyboardVisible) {
            Spacer(Modifier.height(24.dp))

            Text(
                modifier = Modifier.padding(horizontal = 32.dp),
                text = stringResource(R.string.set_currency),
                style = UI.typo.h2.style(
                    fontWeight = FontWeight.Black
                )
            )
        }

        Spacer(Modifier.height(24.dp))

        CurrencyPicker(
            modifier = Modifier
                .fillMaxSize(),
            initialSelectedCurrency = null,
            preselectedCurrency = preselectedCurrency,
            includeKeyboardShownInsetSpacer = true,
            lastItemSpacer = 120.dp,
            onKeyboardShown = { keyboardShown ->
                keyboardVisible = keyboardShown
            }
        ) {
            currency = it
        }
    }

    GradientCutBottom(
        height = 160.dp
    )

    OnboardingButton(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .align(Alignment.BottomCenter)
            .navigationBarsPadding()
            .padding(bottom = 20.dp),

        text = stringResource(R.string.set),
        textColor = White,
        backgroundGradient = GradientRatio,
        hasNext = true,
        enabled = true
    ) {
        onSetCurrency(currency)
    }
}

@Preview
@Composable
private fun Preview() {
    RatioWalletPreview {
        OnboardingSetCurrency(
            preselectedCurrency = RatioCurrency.getDefault()
        ) {
        }
    }
}
