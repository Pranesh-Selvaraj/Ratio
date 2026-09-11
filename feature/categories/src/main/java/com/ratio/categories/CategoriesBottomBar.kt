package com.ratio.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ratio.legacy.RatioWalletPreview
import com.ratio.ui.R
import com.ratio.wallet.ui.theme.Blue
import com.ratio.wallet.ui.theme.components.BackBottomBar
import com.ratio.wallet.ui.theme.components.RatioButton

@Composable
internal fun BoxWithConstraintsScope.CategoriesBottomBar(
    onClose: () -> Unit,
    onAddCategory: () -> Unit
) {
    BackBottomBar(onBack = onClose) {
        RatioButton(
            text = stringResource(R.string.add_category),
            iconStart = R.drawable.ic_plus
        ) {
            onAddCategory()
        }
    }
}

@Preview
@Composable
private fun PreviewBottomBar() {
    com.ratio.legacy.RatioWalletPreview {
        Column(
            Modifier
                .fillMaxSize()
                .background(Blue)
        ) {
        }

        CategoriesBottomBar(
            onAddCategory = {},
            onClose = {}
        )
    }
}
