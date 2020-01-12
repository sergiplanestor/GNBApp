package bemobile.splanes.com.gnbapp.commons.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import bemobile.splanes.com.gnbapp.R
import bemobile.splanes.com.gnbapp.feature.model.TransactionItem
import kotlinx.android.synthetic.main.component_transaction_summary_view.view.*

/**
 * Component used to display transaction summary.
 */
class TransactionSummaryView @JvmOverloads constructor(context: Context,
                                                       attrs: AttributeSet? = null,
                                                       defStyle: Int = 0,
                                                       defStyleRes: Int = 0) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    init {
        bindViews()
    }

    private fun bindViews() {
        View.inflate(context, R.layout.component_transaction_summary_view, this)
    }

    /**
     * Sets transactions info and update view.
     */
    fun setTransactions(transactions: List<TransactionItem>) {
        numOfTransactionsTextView.text = context.getString(R.string.transaction_total_made, transactions.size)
        val total = getTotal(transactions)
        totalTextView.text = context.getString(R.string.transaction_total_sum, total)
        averageTextView.text = context.getString(R.string.transaction_average, (total / transactions.size))
        currentCurrencyTextView.text = transactions[0].currency.value
    }

    /**
     * Private method to calculate total amount from given transactions
     */
    private fun getTotal(transactions: List<TransactionItem>) : Float {
        var result = 0f
        transactions.forEach {
            result += it.amount.toFloat()
        }
        return result
    }
}