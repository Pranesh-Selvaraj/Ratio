package com.ratio

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.runtime.Composable
import com.ratio.attributions.AttributionsScreenImpl
import com.ratio.balance.BalanceScreen
import com.ratio.budgets.BudgetScreen
import com.ratio.categories.CategoriesScreen
import com.ratio.contributors.ContributorsScreenImpl
import com.ratio.disclaimer.DisclaimerScreenImpl
import com.ratio.exchangerates.ExchangeRatesScreen
import com.ratio.features.FeaturesScreenImpl
import com.ratio.importdata.csv.CSVScreen
import com.ratio.importdata.csvimport.ImportCSVScreen
import com.ratio.loans.loan.LoansScreen
import com.ratio.loans.loandetails.LoanDetailsScreen
import com.ratio.main.MainScreen
import com.ratio.navigation.AttributionsScreen
import com.ratio.navigation.BalanceScreen
import com.ratio.navigation.BudgetScreen
import com.ratio.navigation.CSVScreen
import com.ratio.navigation.CategoriesScreen
import com.ratio.navigation.ContributorsScreen
import com.ratio.navigation.DisclaimerScreen
import com.ratio.navigation.EditPlannedScreen
import com.ratio.navigation.EditTransactionScreen
import com.ratio.navigation.ExchangeRatesScreen
import com.ratio.navigation.FeaturesScreen
import com.ratio.navigation.ImportScreen
import com.ratio.navigation.LoanDetailsScreen
import com.ratio.navigation.LoansScreen
import com.ratio.navigation.MainScreen
import com.ratio.navigation.OnboardingScreen
import com.ratio.navigation.PieChartStatisticScreen
import com.ratio.navigation.PlannedPaymentsScreen
import com.ratio.navigation.ReleasesScreen
import com.ratio.navigation.ReportScreen
import com.ratio.navigation.Screen
import com.ratio.navigation.SearchScreen
import com.ratio.navigation.SettingsScreen
import com.ratio.navigation.TransactionsScreen
import com.ratio.onboarding.OnboardingScreen
import com.ratio.piechart.PieChartStatisticScreen
import com.ratio.planned.edit.EditPlannedScreen
import com.ratio.planned.list.PlannedPaymentsScreen
import com.ratio.releases.ReleasesScreenImpl
import com.ratio.reports.ReportScreen
import com.ratio.search.SearchScreen
import com.ratio.settings.SettingsScreen
import com.ratio.transaction.EditTransactionScreen
import com.ratio.transactions.TransactionsScreen

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
@Suppress("CyclomaticComplexMethod", "FunctionNaming")
fun BoxWithConstraintsScope.RatioNavGraph(screen: Screen?) {
    when (screen) {
        null -> {
            // show nothing
        }

        is MainScreen -> MainScreen(screen = screen)
        is OnboardingScreen -> OnboardingScreen(screen = screen)
        is ExchangeRatesScreen -> ExchangeRatesScreen()
        is EditTransactionScreen -> EditTransactionScreen(screen = screen)
        is TransactionsScreen -> TransactionsScreen(screen = screen)
        is PieChartStatisticScreen -> PieChartStatisticScreen(screen = screen)
        is CategoriesScreen -> CategoriesScreen(screen = screen)
        is SettingsScreen -> SettingsScreen()
        is PlannedPaymentsScreen -> PlannedPaymentsScreen(screen = screen)
        is EditPlannedScreen -> EditPlannedScreen(screen = screen)
        is BalanceScreen -> BalanceScreen(screen = screen)
        is ImportScreen -> ImportCSVScreen(screen = screen)
        is ReportScreen -> ReportScreen(screen = screen)
        is BudgetScreen -> BudgetScreen(screen = screen)
        is LoansScreen -> LoansScreen(screen = screen)
        is LoanDetailsScreen -> LoanDetailsScreen(screen = screen)
        is SearchScreen -> SearchScreen(screen = screen)
        is CSVScreen -> CSVScreen(screen = screen)
        FeaturesScreen -> FeaturesScreenImpl()
        AttributionsScreen -> AttributionsScreenImpl()
        ContributorsScreen -> ContributorsScreenImpl()
        ReleasesScreen -> ReleasesScreenImpl()
        DisclaimerScreen -> DisclaimerScreenImpl()
    }
}
