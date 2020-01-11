package bemobile.splanes.com.gnbapp.feature.ui.adapter

import bemobile.splanes.com.gnbapp.feature.model.Transaction

interface OnTransactionClickListener {
    fun onTransactionClick(transaction: Transaction)
}