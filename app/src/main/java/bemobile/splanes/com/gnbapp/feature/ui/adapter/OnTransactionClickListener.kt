package bemobile.splanes.com.gnbapp.feature.ui.adapter

import bemobile.splanes.com.gnbapp.feature.model.TransactionItem

interface OnTransactionClickListener {
    fun onTransactionClick(transaction: TransactionItem)
}