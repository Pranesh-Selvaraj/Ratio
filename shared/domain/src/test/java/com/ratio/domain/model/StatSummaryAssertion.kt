package com.ratio.domain.model

import com.ratio.data.model.testing.shouldBeApprox
import io.kotest.matchers.shouldBe

infix fun StatSummary.shouldBeApprox(other: StatSummary) {
    trnCount shouldBe other.trnCount
    values.keys shouldBe other.values.keys
    values.keys.forEach { key ->
        values[key]!!.value shouldBeApprox other.values[key]!!.value
    }
}