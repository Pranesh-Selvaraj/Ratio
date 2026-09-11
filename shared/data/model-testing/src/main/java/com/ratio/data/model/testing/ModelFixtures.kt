package com.ratio.data.model.testing

import com.ratio.data.model.AccountId
import com.ratio.data.model.CategoryId
import com.ratio.data.model.TransactionId
import java.util.UUID

object ModelFixtures {
    val AccountId = AccountId(UUID.randomUUID())
    val CategoryId = CategoryId(UUID.randomUUID())
    val TransactionId = TransactionId(UUID.randomUUID())
}
