package com.ratio.wallet.domain.data

import android.icu.util.Currency
import androidx.compose.runtime.Immutable
import com.ratio.legacy.utils.getDefaultFIATCurrency

@Immutable
data class RatioCurrency(
    val code: String,
    val name: String,
    val isCrypto: Boolean
) {
    companion object {
        private const val CRYPTO_DECIMAL = 18
        private const val FIAT_DECIMAL = 2

        private val CRYPTO = setOf(
            RatioCurrency(
                code = "BTC",
                name = "Bitcoin",
                isCrypto = true
            ),
            RatioCurrency(
                code = "ETH",
                name = "Ethereum",
                isCrypto = true
            ),
            RatioCurrency(
                code = "USDT",
                name = "Tether USD",
                isCrypto = true
            ),
            RatioCurrency(
                code = "BNB",
                name = "Binance Coin",
                isCrypto = true
            ),
            RatioCurrency(
                code = "ADA",
                name = "Cardano",
                isCrypto = true
            ),
            RatioCurrency(
                code = "XRP",
                name = "Ripple",
                isCrypto = true
            ),
            RatioCurrency(
                code = "DOGE",
                name = "Dogecoin",
                isCrypto = true
            ),
            RatioCurrency(
                code = "USDC",
                name = "USD Coin",
                isCrypto = true
            ),
            RatioCurrency(
                code = "DOT",
                name = "Polkadot",
                isCrypto = true
            ),
            RatioCurrency(
                code = "UNI",
                name = "Uniswap",
                isCrypto = true
            ),
            RatioCurrency(
                code = "BUSD",
                name = "Binance USD",
                isCrypto = true
            ),
            RatioCurrency(
                code = "BCH",
                name = "Bitcoin Cash",
                isCrypto = true
            ),
            RatioCurrency(
                code = "SOL",
                name = "Solana",
                isCrypto = true
            ),
            RatioCurrency(
                code = "LTC",
                name = "Litecoin",
                isCrypto = true
            ),
            RatioCurrency(
                code = "LINK",
                name = "ChainLink Token",
                isCrypto = true
            ),
            RatioCurrency(
                code = "SHIB",
                name = "Shiba Inu coin",
                isCrypto = true
            ),
            RatioCurrency(
                code = "LUNA",
                name = "Terra",
                isCrypto = true
            ),
            RatioCurrency(
                code = "AVAX",
                name = "Avalanche",
                isCrypto = true
            ),
            RatioCurrency(
                code = "MATIC",
                name = "Polygon",
                isCrypto = true
            ),
            RatioCurrency(
                code = "CRO",
                name = "Cronos",
                isCrypto = true
            ),
            RatioCurrency(
                code = "WBTC",
                name = "Wrapped Bitcoin",
                isCrypto = true
            ),
            RatioCurrency(
                code = "ALGO",
                name = "Algorand",
                isCrypto = true
            ),
            RatioCurrency(
                code = "XLM",
                name = "Stellar",
                isCrypto = true
            ),
            RatioCurrency(
                code = "MANA",
                name = "Decentraland",
                isCrypto = true
            ),
            RatioCurrency(
                code = "AXS",
                name = "Axie Infinity",
                isCrypto = true
            ),
            RatioCurrency(
                code = "DAI",
                name = "Dai",
                isCrypto = true
            ),
            RatioCurrency(
                code = "ICP",
                name = "Internet Computer",
                isCrypto = true
            ),
            RatioCurrency(
                code = "ATOM",
                name = "Cosmos",
                isCrypto = true
            ),
            RatioCurrency(
                code = "FIL",
                name = "Filecoin",
                isCrypto = true
            ),
            RatioCurrency(
                code = "ETC",
                name = "Ethereum Classic",
                isCrypto = true
            ),
            RatioCurrency(
                code = "DASH",
                name = "Dash",
                isCrypto = true
            ),
            RatioCurrency(
                code = "TRX",
                name = "Tron",
                isCrypto = true
            ),
            RatioCurrency(
                code = "TON",
                name = "Tonchain",
                isCrypto = true
            ),
        )

        fun getAvailable(): List<RatioCurrency> {
            return Currency.getAvailableCurrencies()
                .map {
                    RatioCurrency(
                        code = it.currencyCode,
                        name = it.displayName,
                        isCrypto = false
                    )
                }
                .plus(CRYPTO)
        }

        fun fromCode(code: String): RatioCurrency? {
            if (code.isBlank()) return null

            val crypto = CRYPTO.find { it.code == code }
            if (crypto != null) {
                return crypto
            }

            return try {
                val fiat = Currency.getInstance(code)
                RatioCurrency(
                    fiatCurrency = fiat
                )
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

        fun getDefault(): RatioCurrency = RatioCurrency(
            fiatCurrency = getDefaultFIATCurrency()
        )

        fun getDecimalPlaces(assetCode: String): Int =
            if (fromCode(assetCode) in CRYPTO) CRYPTO_DECIMAL else FIAT_DECIMAL
    }

    constructor(fiatCurrency: Currency) : this(
        code = fiatCurrency.currencyCode,
        name = fiatCurrency.displayName,
        isCrypto = false
    )
}
