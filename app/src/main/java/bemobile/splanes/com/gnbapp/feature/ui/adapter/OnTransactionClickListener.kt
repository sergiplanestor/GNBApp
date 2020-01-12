package bemobile.splanes.com.gnbapp.feature.ui.adapter

import bemobile.splanes.com.gnbapp.feature.model.TransactionItem

/**
 * Interface to handle when user clicks on transaction
 */
interface OnTransactionClickListener {
    fun onTransactionClick(transaction: TransactionItem)
}