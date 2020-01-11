package bemobile.splanes.com.gnbapp.feature.ui

import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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

    override fun loadData() {
        super.loadData()
        mViewModel.getProducts().observe(this, Observer<List<ProductItem>> { products ->
            runOnUiThread {
                adapter.updateData(products = products)
                hideLoader()
            }
        })
    }

    override fun updateViews() {
        super.updateViews()
        loadData()
    }

    override fun onProductClick(product: ProductItem) {
        launchActivity(TransactionActivity::class.java, Bundle().apply {
            putParcelable(TransactionActivity.EXTRA_PRODUCT, product)
        })
    }

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
