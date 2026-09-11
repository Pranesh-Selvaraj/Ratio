package com.ratio.releases

sealed interface ReleasesEvent {
    data object OnTryAgainClick : ReleasesEvent
}
