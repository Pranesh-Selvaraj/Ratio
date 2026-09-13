package com.ratio.home.customerjourney

import com.ratio.base.legacy.SharedPrefs
import com.ratio.base.legacy.stringRes
import com.ratio.base.model.TransactionType
import com.ratio.base.time.TimeProvider
import com.ratio.data.db.dao.read.PlannedPaymentRuleDao
import com.ratio.data.repository.TransactionRepository
import com.ratio.design.l0_system.Gradient
import com.ratio.design.l0_system.GreenLight
import com.ratio.design.l0_system.Ratio
import com.ratio.design.l0_system.Orange
import com.ratio.design.l0_system.Red
import com.ratio.legacy.RatioCtx
import com.ratio.legacy.data.model.MainTab
import com.ratio.navigation.EditPlannedScreen
import com.ratio.navigation.PieChartStatisticScreen
import com.ratio.ui.R
import com.ratio.widget.transaction.AddTransactionWidgetCompact
import javax.inject.Inject

@Deprecated("Legacy code")
class CustomerJourneyCardsProvider @Inject constructor(
  private val transactionRepository: TransactionRepository,
  private val plannedPaymentRuleDao: PlannedPaymentRuleDao,
  private val sharedPrefs: SharedPrefs,
  private val ratioContext: RatioCtx,
  private val timeProvider: TimeProvider,
) {

  suspend fun loadCards(): List<CustomerJourneyCardModel> {
    val trnCount = transactionRepository.countHappenedTransactions().value
    val plannedPaymentsCount = plannedPaymentRuleDao.countPlannedPayments()
    val deps = CustomerJourneyDeps(
      timeProvider = timeProvider,
    )

    return ACTIVE_CARDS
      .filter {
        it.condition(
          trnCount,
          plannedPaymentsCount,
          ratioContext,
          deps
        ) && !isCardDismissed(it)
      }
  }

  private fun isCardDismissed(cardData: CustomerJourneyCardModel): Boolean {
    return sharedPrefs.getBoolean(sharedPrefsKey(cardData), false)
  }

  fun dismissCard(cardData: CustomerJourneyCardModel) {
    sharedPrefs.putBoolean(sharedPrefsKey(cardData), true)
  }

  private fun sharedPrefsKey(cardData: CustomerJourneyCardModel): String {
    return "${cardData.id}${SharedPrefs._CARD_DISMISSED}"
  }

  companion object {
    val ACTIVE_CARDS = listOf(
      adjustBalanceCard(),
      addPlannedPaymentCard(),
      didYouKnow_pinAddTransactionWidgetCard(),
      didYouKnow_expensesPieChart()
    )

    fun adjustBalanceCard() = CustomerJourneyCardModel(
      id = "adjust_balance",
      condition = { trnCount, _, _, _ ->
        trnCount == 0L
      },
      title = stringRes(R.string.adjust_initial_balance),
      description = stringRes(R.string.adjust_initial_balance_description),
      cta = stringRes(R.string.to_accounts),
      ctaIcon = R.drawable.ic_custom_account_s,
      background = Gradient.solid(Ratio),
      hasDismiss = false,
      onAction = { _, ratioContext, _ ->
        ratioContext.selectMainTab(MainTab.ACCOUNTS)
      }
    )

    fun addPlannedPaymentCard() = CustomerJourneyCardModel(
      id = "add_planned_payment",
      condition = { trnCount, plannedPaymentCount, _, _ ->
        trnCount >= 1 && plannedPaymentCount == 0L
      },
      title = stringRes(R.string.create_first_planned_payment),
      description = stringRes(R.string.create_first_planned_payment_description),
      cta = stringRes(R.string.add_planned_payment),
      ctaIcon = R.drawable.ic_planned_payments,
      background = Gradient.solid(Orange),
      hasDismiss = true,
      onAction = { navigation, _, _ ->
        navigation.navigateTo(
          EditPlannedScreen(
            type = TransactionType.EXPENSE,
            plannedPaymentRuleId = null
          )
        )
      }
    )

    fun didYouKnow_pinAddTransactionWidgetCard() = CustomerJourneyCardModel(
      id = "add_transaction_widget",
      condition = { trnCount, _, _, _ ->
        trnCount >= 3
      },
      title = stringRes(R.string.did_you_know),
      description = stringRes(R.string.widget_description),
      cta = stringRes(R.string.add_widget),
      ctaIcon = R.drawable.ic_custom_atom_s,
      background = Gradient.solid(GreenLight),
      hasDismiss = true,
      onAction = { _, _, ratioActivity ->
        ratioActivity.pinWidget(AddTransactionWidgetCompact::class.java)
      }
    )

    fun didYouKnow_expensesPieChart() = CustomerJourneyCardModel(
      id = "expenses_pie_chart",
      condition = { trnCount, _, _, _ ->
        trnCount >= 7
      },
      title = stringRes(R.string.did_you_know),
      description = stringRes(R.string.you_can_see_a_piechart),
      cta = stringRes(R.string.expenses_piechart),
      ctaIcon = R.drawable.ic_custom_bills_s,
      background = Gradient.solid(Red),
      hasDismiss = true,
      onAction = { navigation, _, _ ->
        navigation.navigateTo(PieChartStatisticScreen(type = TransactionType.EXPENSE))
      }
    )
  }
}
