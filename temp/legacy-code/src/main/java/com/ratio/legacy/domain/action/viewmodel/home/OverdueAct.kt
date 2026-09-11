package com.ratio.wallet.domain.action.viewmodel.home

import com.ratio.frp.action.FPAction
import com.ratio.frp.then
import com.ratio.legacy.utils.ratioMinTime
import com.ratio.wallet.domain.pure.data.ClosedTimeRange
import com.ratio.wallet.domain.pure.data.IncomeExpensePair
import com.ratio.wallet.domain.pure.transaction.isOverdue
import java.time.Instant
import javax.inject.Inject

class OverdueAct @Inject constructor(
    private val dueTrnsInfoAct: DueTrnsInfoAct
) : FPAction<OverdueAct.Input, OverdueAct.Output>() {

    override suspend fun Input.compose(): suspend () -> Output = suspend {
        DueTrnsInfoAct.Input(
            range = ClosedTimeRange(
                from = ratioMinTime(),
                to = toRange
            ),
            baseCurrency = baseCurrency,
            dueFilter = ::isOverdue
        )
    } then dueTrnsInfoAct then {
        Output(
            overdue = it.dueIncomeExpense,
            overdueTrns = it.dueTrns
        )
    }

    data class Input(
        val toRange: Instant,
        val baseCurrency: String
    )

    data class Output(
        val overdue: IncomeExpensePair,
        val overdueTrns: List<com.ratio.data.model.Transaction>
    )
}
