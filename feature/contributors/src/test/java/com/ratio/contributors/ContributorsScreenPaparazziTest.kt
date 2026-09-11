package com.ratio.contributors

import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.ratio.ui.testing.PaparazziScreenshotTest
import com.ratio.ui.testing.PaparazziTheme
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class ContributorsScreenPaparazziTest(
    @TestParameter
    private val theme: PaparazziTheme,
) : PaparazziScreenshotTest() {
    @Test
    fun `snapshot Contributor Screen`() {
        snapshot(theme) {
            ContributorScreenUiTest(theme == PaparazziTheme.Dark)
        }
    }
}