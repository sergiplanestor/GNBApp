package bemobile.splanes.com.gnbapp.feature.transaction.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bemobile.splanes.com.gnbapp.commons.ui.component.ProductView
import bemobile.splanes.com.gnbapp.feature.transaction.model.ProductItem

/**
 * Product's adapter
 */
class ProductAdapter(private val context : Context,
                     private var products: List<ProductItem>,
                     private val listener: OnProductClickListener?) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder = ViewHolder(ProductView(context))

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val view = holder.itemView as ProductView
        val data = products[position]

        view.setProductName(data.name)
        view.setCardClickListener(View.OnClickListener { listener?.onProductClick(data) })
    }

    fun updateData(products: List<ProductItem>) {
        this.products = products
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}