package com.ratio.design.l1_buildingBlocks

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.ratio.design.l1_buildingBlocks.data.RatioPadding
import com.ratio.design.utils.ratioPadding
import com.ratio.design.utils.thenIf

@Deprecated("Old design system. Use `:ratio-design` and Material3")
@Composable
fun RatioText(
    modifier: Modifier = Modifier,
    text: String,
    typo: TextStyle,
    padding: RatioPadding? = null
) {
    Text(
        modifier = Modifier
            .thenIf(padding != null) {
                ratioPadding(ratioPadding = padding!!)
            }
            .then(modifier),
        text = text,
        style = typo,
    )
}
