package bemobile.splanes.com.gnbapp.feature.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import bemobile.splanes.com.gnbapp.commons.ui.component.TransactionView
import bemobile.splanes.com.gnbapp.feature.model.TransactionItem

/**
 * Transactions adapter.
 */
class TransactionAdapter(private val context: Context,
                         private var transactions: List<TransactionItem>,
                         private val listener: OnTransactionClickListener? = null) : RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = TransactionView(context)
        view.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int =  transactions.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view = holder.itemView as TransactionView
        val data = transactions[position]

        view.setSku(data.sku)
        view.setAmount(data.amount)
        view.setCurrency(data.currency.value)
        view.setCardClickListener(View.OnClickListener {
            listener?.onTransactionClick(data)
        })
    }

    fun updateData(transactions: List<TransactionItem>) {
        this.transactions = transactions
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}