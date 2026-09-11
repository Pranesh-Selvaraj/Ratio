package com.ratio.wallet.domain.action.viewmodel.home

import com.ratio.frp.action.FPAction
import com.ratio.legacy.RatioCtx
import com.ratio.legacy.datamodel.Account
import javax.inject.Inject

class UpdateAccCacheAct @Inject constructor(
    private val ratioCtx: RatioCtx
) : FPAction<List<Account>, List<Account>>() {
    override suspend fun List<Account>.compose(): suspend () -> List<Account> = suspend {
        val accounts = this

        ratioCtx.accountMap.clear()
        ratioCtx.accountMap.putAll(accounts.map { it.id to it })

        accounts
    }
}
