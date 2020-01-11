package bemobile.splanes.com.gnbapp.feature.ui

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import bemobile.splanes.com.gnbapp.R
import bemobile.splanes.com.gnbapp.commons.extension.app
import bemobile.splanes.com.gnbapp.commons.ui.base.BaseActivity
import bemobile.splanes.com.gnbapp.feature.dagger.TransactionModule
import bemobile.splanes.com.gnbapp.feature.model.ProductItem
import bemobile.splanes.com.gnbapp.feature.ui.adapter.OnProductClickListener
import bemobile.splanes.com.gnbapp.feature.ui.adapter.ProductAdapter
import bemobile.splanes.com.gnbapp.feature.viewmodel.TransactionViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<TransactionViewModel>(), OnProductClickListener {

    lateinit var adapter: ProductAdapter

// =================================================================================================
// Init views
// =================================================================================================

    override fun initViews() {
        super.initViews()

        setSupportActionBar(toolbar)
        collapsingToolbar.title = getString(R.string.brand)

        adapter = ProductAdapter(context = this, products = listOf(), listener =  this)
        transactionRecyclerView.layoutManager = GridLayoutManager(this, 2)
        transactionRecyclerView.layoutAnimation =
            AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down)
        transactionRecyclerView.adapter = adapter
    }

// =================================================================================================
// Load data & update views
// =================================================================================================

    override fun loadData() {
        super.loadData()
        mViewModel.getProducts().observe(this, Observer { products ->
            runOnUiThread {
                hideLoader()
                if (products == null) {
                    showEmptyState()
                    showErrorPopUp()
                } else {
                    hideEmptyState()
                    adapter.updateData(products = products)
                }
            }
        })
    }

    override fun updateViews() {
        super.updateViews()
        loadData()
    }

    private fun showEmptyState() {
        emptyStateView.visibility = View.VISIBLE
        emptyStateView.setButtonClickListener(View.OnClickListener {
            loadData()
        })
    }

    private fun hideEmptyState() {
        emptyStateView.visibility = View.GONE
    }

// =================================================================================================
// OnProductClickListener Implementation
// =================================================================================================

    override fun onProductClick(product: ProductItem) {
        launchActivity(TransactionActivity::class.java, Bundle().apply {
            putParcelable(TransactionActivity.EXTRA_PRODUCT, product)
        })
    }

// =================================================================================================
// Parent's abstract methods implementation
// =================================================================================================

    override fun getLayoutResource(): Int {
        return R.layout.activity_main
    }

    override fun getViewModelClass(): Class<TransactionViewModel> {
        return TransactionViewModel::class.java
    }

    override fun injectDaggerComponent() {
        app.component.plus(TransactionModule()).inject(this)
    }
}
