package bemobile.splanes.com.gnbapp.feature.ui.adapter

import bemobile.splanes.com.gnbapp.feature.model.ProductItem

/**
 * Interface to handle when user clicks on product
 */
interface OnProductClickListener {
    fun onProductClick(product: ProductItem)
}