package bemobile.splanes.com.gnbapp.feature.ui

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import bemobile.splanes.com.gnbapp.R
import bemobile.splanes.com.gnbapp.commons.extension.app
import bemobile.splanes.com.gnbapp.commons.ui.base.BaseActivity
import bemobile.splanes.com.gnbapp.feature.dagger.TransactionModule
import bemobile.splanes.com.gnbapp.feature.model.Transaction
import bemobile.splanes.com.gnbapp.feature.service.GNBService
import bemobile.splanes.com.gnbapp.feature.ui.adapter.OnTransactionClickListener
import bemobile.splanes.com.gnbapp.feature.ui.adapter.TransactionAdapter
import bemobile.splanes.com.gnbapp.feature.viewmodel.TransactionViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<TransactionViewModel>(), OnTransactionClickListener {

    @Inject
    lateinit var service: GNBService

    lateinit var adapter: TransactionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun initViews() {
        super.initViews()

        collapsingToolbar.title = "Goliath National Bank"

        adapter = TransactionAdapter(context = this, transactions = listOf(), listener =  this)
        transactionRecyclerView.layoutManager = LinearLayoutManager(this)
        transactionRecyclerView.layoutAnimation =
            AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down)
        transactionRecyclerView.adapter = adapter
    }

    override fun loadData() {
        super.loadData()
        mViewModel.getTransactions().observe(this, Observer<List<Transaction>> { transactions ->
            runOnUiThread {
                adapter.updateData(transactions = transactions)
                hideLoader()
            }
        })
    }

    override fun onTransactionClick(transaction: Transaction) {
        Toast.makeText(this, transaction.sku, Toast.LENGTH_SHORT).show()
    }

    override fun getViewModel(): Class<TransactionViewModel> {
        return TransactionViewModel::class.java
    }

    /*override fun getViewModelFactory(): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return TransactionViewModel() as T
            }
        }
    }*/

    override fun injectDaggerComponent() {
        app.component.plus(TransactionModule()).inject(this)
    }
}
