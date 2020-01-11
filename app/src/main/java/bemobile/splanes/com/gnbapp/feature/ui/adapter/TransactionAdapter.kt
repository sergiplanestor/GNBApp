package bemobile.splanes.com.gnbapp.feature.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import bemobile.splanes.com.gnbapp.commons.ui.component.TransactionView
import bemobile.splanes.com.gnbapp.feature.model.Transaction

class TransactionAdapter(private val context : Context,
                         private var transactions: List<Transaction>,
                         private val listener: OnTransactionClickListener?) : RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v : View = TransactionView(context)
        v.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        return ViewHolder(v)
    }


    override fun getItemCount(): Int {
        return transactions.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val view = holder.itemView as TransactionView
        val data = transactions[position]

        view.setSku(data.sku)
        view.setAmount(data.amount)
        view.setCurrency(data.currency.value)
        view.setCardClickListener(View.OnClickListener { listener?.onTransactionClick(data) })
    }

    fun updateData(transactions: List<Transaction>) {
        this.transactions = transactions
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}