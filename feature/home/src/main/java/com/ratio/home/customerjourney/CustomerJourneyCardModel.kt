package com.ratio.home.customerjourney

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import com.ratio.base.time.TimeProvider
import com.ratio.design.l0_system.Gradient
import com.ratio.domain.RootScreen
import com.ratio.legacy.RatioCtx
import com.ratio.navigation.Navigation

@Immutable
data class CustomerJourneyCardModel(
    val id: String,
    @Suppress("MaximumLineLength", "ParameterWrapping", "MaxLineLength", "ParameterListWrapping")
    val condition: suspend (trnCount: Long, plannedPaymentsCount: Long, ratioContext: RatioCtx, deps: CustomerJourneyDeps) -> Boolean,
    val title: String,
    val description: String,
    val cta: String?,
    @DrawableRes val ctaIcon: Int,

    val hasDismiss: Boolean = true,

    val background: Gradient,
    val onAction: (Navigation, RatioCtx, RootScreen) -> Unit
)

@Immutable
data class CustomerJourneyDeps(
    val timeProvider: TimeProvider,
)
