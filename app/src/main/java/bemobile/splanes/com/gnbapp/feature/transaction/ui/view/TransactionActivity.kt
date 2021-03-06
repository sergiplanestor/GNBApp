package bemobile.splanes.com.gnbapp.feature.transaction.ui.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Parcel
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import bemobile.splanes.com.gnbapp.R
import bemobile.splanes.com.gnbapp.commons.extension.app
import bemobile.splanes.com.gnbapp.commons.ui.base.BaseActivity
import bemobile.splanes.com.gnbapp.feature.transaction.dagger.TransactionModule
import bemobile.splanes.com.gnbapp.feature.transaction.model.CurrencyType
import bemobile.splanes.com.gnbapp.feature.transaction.model.ProductItem
import bemobile.splanes.com.gnbapp.feature.transaction.model.TransactionItem
import bemobile.splanes.com.gnbapp.feature.transaction.ui.adapter.TransactionAdapter
import bemobile.splanes.com.gnbapp.feature.transaction.ui.viewmodel.TransactionViewModel
import kotlinx.android.synthetic.main.activity_transaction.*

class TransactionActivity() : BaseActivity<TransactionViewModel>(), View.OnClickListener,
    DialogInterface.OnClickListener {

    companion object EXTRAS {
        const val EXTRA_PRODUCT = "product"
    }

    private lateinit var product : ProductItem

    private lateinit var adapter: TransactionAdapter

    private lateinit var popup: AlertDialog

    private var currentCurrency: CurrencyType = CurrencyType.EUR

// =================================================================================================
// Setup views
// =================================================================================================

    override fun initViews() {
        super.initViews()
        product = intent.getParcelableExtra(EXTRA_PRODUCT)
        setupToolbar()
        setupAdapter()
        setupChangeCurrency()
    }

    private fun setupAdapter() {
        adapter = TransactionAdapter(this, listOf())
        transactionRecyclerView.layoutManager = LinearLayoutManager(this)
        transactionRecyclerView.layoutAnimation =
            AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down)
        transactionRecyclerView.adapter = adapter
    }

    private fun setupChangeCurrency() {
        val elements = CurrencyType.values().mapTo(
            listOf<String>().toMutableList(),
            fun(item) : String = item.value
        )
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.change_currency_dialog_title)
        builder.setSingleChoiceItems(elements.toTypedArray(), elements.indexOf(currentCurrency.value))
        { _, which ->
            currentCurrency = CurrencyType.values()[which]
        }
        builder.setPositiveButton(R.string.accept, this)
        builder.setNegativeButton(R.string.cancel, this)
        popup = builder.create()
        changeCurrencyFab.setOnClickListener(this)
    }

    override fun loadData() {
        super.loadData()

        mViewModel.getTransactions(product, currentCurrency).observe(this, Observer<List<TransactionItem>> { transactions ->
            runOnUiThread {
                transactionSummaryView.setTransactions(transactions)
                adapter.updateData(transactions)
                hideLoader()
            }
        })
    }

    override fun updateViews() {
        super.updateViews()
        loadData()
    }

// =================================================================================================
// Toolbar
// =================================================================================================

    private fun setupToolbar()  {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.transaction_detail, product.name)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

// =================================================================================================
// View.OnClickListener Implementation
// =================================================================================================

    override fun onClick(v: View?) {
        popup.show()
    }

// =================================================================================================
// DialogInterface.OnClickListener Implementation
// =================================================================================================

    override fun onClick(dialog: DialogInterface?, which: Int) {
        if (which == DialogInterface.BUTTON_POSITIVE) {
            updateViews()
        }
    }

// =================================================================================================
// Abstract methods implementation from parent
// =================================================================================================

    override fun getLayoutResource(): Int {
        return R.layout.activity_transaction
    }

    override fun getViewModelClass(): Class<TransactionViewModel> {
        return TransactionViewModel::class.java
    }

    override fun injectDaggerComponent() {
        app.component.plus(TransactionModule()).inject(this)
    }
}