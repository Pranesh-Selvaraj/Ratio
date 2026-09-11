package com.ratio.domain.usecase.csv

import com.ratio.base.model.TransactionType
import com.ratio.data.model.AccountId
import com.ratio.data.model.CategoryId
import com.ratio.data.model.TransactionId
import com.ratio.data.model.primitive.AssetCode
import com.ratio.data.model.primitive.NonNegativeDouble
import com.ratio.data.model.primitive.NotBlankTrimmedString
import com.ratio.data.model.primitive.PositiveDouble
import java.time.Instant

// TODO: Fix Ratio Explicit detekt false-positives
@SuppressWarnings("DataClassTypedIDs")
data class RatioCsvRow(
    val date: Instant?,
    val title: NotBlankTrimmedString?,
    val category: CategoryId?,
    val account: AccountId,
    val amount: NonNegativeDouble,
    val currency: AssetCode,
    val type: TransactionType,
    val transferAmount: PositiveDouble?,
    val transferCurrency: AssetCode?,
    val toAccountId: AccountId?,
    val receiveAmount: PositiveDouble?,
    val receiveCurrency: AssetCode?,
    val description: NotBlankTrimmedString?,
    val dueData: Instant?,
    val id: TransactionId
) {
    companion object {
        val Columns = listOf(
            "Date",
            "Title",
            "Category",
            "Account",
            "Amount",
            "Currency",
            "Type",
            "Transfer Amount",
            "Transfer Currency",
            "To Account",
            "Receive Amount",
            "Receive Currency",
            "Description",
            "Due Date",
            "ID",
        )
    }
}