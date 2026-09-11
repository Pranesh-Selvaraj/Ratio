package com.ratio.loans

import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.ratio.loans.loan.LoanScreenUiTest
import com.ratio.ui.testing.PaparazziScreenshotTest
import com.ratio.ui.testing.PaparazziTheme
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class LoanScreenPaparazziTest(
    @TestParameter
    private val theme: PaparazziTheme,
) : PaparazziScreenshotTest() {
    @Test
    fun `snapshot loanScreen composable`() {
        snapshot(theme) {
            LoanScreenUiTest(theme == PaparazziTheme.Dark)
        }
    }
}