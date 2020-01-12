package bemobile.splanes.com.gnbapp.commons.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import bemobile.splanes.com.gnbapp.R
import kotlinx.android.synthetic.main.component_transaction_view.view.*

/**
 * Component used to display transactions.
 */
class TransactionView @JvmOverloads constructor(context: Context,
                                                attrs: AttributeSet? = null,
                                                defStyle: Int = 0,
                                                defStyleRes: Int = 0) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    init {
        bindViews()
    }

    private fun bindViews() {
        View.inflate(context, R.layout.component_transaction_view, this)
    }

    fun setSku(sku: String) {
        skuTextView.text = sku
    }

    fun setAmount(amount: String) {
        amountTextView.text = amount
    }

    fun setCurrency(currency: String) {
        currencyTextView.text = currency
    }

    fun setCardClickListener(onClickListener: OnClickListener) {
        cardView.setOnClickListener(onClickListener)
    }
}