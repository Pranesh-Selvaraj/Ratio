package com.ratio.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ratio.design.system.colors.RatioColors
import com.ratio.ui.R

const val RatioGitHubRepoUrl = "https://github.com/Pranesh-Selvaraj/Ratio"

@Composable
fun OpenSourceCard(
    modifier: Modifier = Modifier,
) {
    val uriHandler = LocalUriHandler.current
    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        onClick = {
            uriHandler.openUri(RatioGitHubRepoUrl)
        }
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 12.dp,
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GitHubLogo()
            Spacer(modifier = Modifier.width(12.dp))
            OpenSourceTexts()
        }
    }
}

@Composable
private fun GitHubLogo(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.github_logo),
        contentDescription = null,
    )
}

@Composable
private fun OpenSourceTexts(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = stringResource(R.string.ratio_open_source),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = RatioGitHubRepoUrl,
            style = MaterialTheme.typography.labelSmall,
            color = RatioColors.Blue.primary
        )
    }
}