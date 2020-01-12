package bemobile.splanes.com.gnbapp.feature.transaction.ui.adapter

import bemobile.splanes.com.gnbapp.feature.transaction.model.ProductItem

/**
 * Interface to handle when user clicks on product
 */
interface OnProductClickListener {
    fun onProductClick(product: ProductItem)
}