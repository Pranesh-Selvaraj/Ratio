package com.ratio.wallet.migrations

interface Migration {
    val key: String

    suspend fun migrate()
}
