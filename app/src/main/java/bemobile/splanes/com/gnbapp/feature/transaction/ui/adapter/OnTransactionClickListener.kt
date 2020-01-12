package bemobile.splanes.com.gnbapp.feature.transaction.ui.adapter

import bemobile.splanes.com.gnbapp.feature.transaction.model.TransactionItem

/**
 * Interface to handle when user clicks on transaction
 */
interface OnTransactionClickListener {
    fun onTransactionClick(transaction: TransactionItem)
}