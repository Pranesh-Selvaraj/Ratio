package com.ratio.legacy

import com.ratio.base.legacy.stringRes
import com.ratio.data.model.LoanType
import com.ratio.legacy.datamodel.Loan
import com.ratio.ui.R

fun Loan.humanReadableType(): String {
    return if (type == LoanType.BORROW) {
        stringRes(R.string.borrowed_uppercase)
    } else {
        stringRes(R.string.lent_uppercase)
    }
}
