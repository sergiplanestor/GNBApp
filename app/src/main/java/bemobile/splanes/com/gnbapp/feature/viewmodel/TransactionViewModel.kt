package bemobile.splanes.com.gnbapp.feature.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import bemobile.splanes.com.gnbapp.commons.ui.base.BaseViewModel
import bemobile.splanes.com.gnbapp.feature.TransactionManager
import bemobile.splanes.com.gnbapp.feature.model.*
import bemobile.splanes.com.gnbapp.feature.service.GNBService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionViewModel constructor(private val mService: GNBService) : BaseViewModel() {


    fun getTransactions(productItem: ProductItem, currencyType: CurrencyType) : LiveData<List<TransactionItem>> {

        val liveData = MutableLiveData<List<TransactionItem>>()
        val transactions = TransactionManager.getTransactions(productItem, currencyType)
        if (transactions != null) {
            liveData.postValue(transactions)
        }

        return liveData
    }

    fun getProducts() : LiveData<List<ProductItem>?> {

        val liveData = MutableLiveData<List<ProductItem>>()

        if (TransactionManager.getProducts() != null) {
            liveData.postValue(TransactionManager.getProducts()!!)
        } else {

            getRates()

            mService.fetchTransactions(object : Callback<List<Transaction>> {

                override fun onFailure(call: Call<List<Transaction>>, t: Throwable) {
                    liveData.postValue(null)
                }

                override fun onResponse(call: Call<List<Transaction>>, response: Response<List<Transaction>>) {
                    if (response.isSuccessful) {
                        TransactionManager.mapProductTransactions(
                            response.body()!!.mapTo(
                                listOf<TransactionItem>().toMutableList(),
                                fun (item) : TransactionItem = TransactionItem(item)
                            )
                        )

                        liveData.postValue(TransactionManager.getProducts())
                    } else {
                        liveData.postValue(null)
                    }
                }
            })
        }
        return liveData
    }

    private fun getRates() {

        if (TransactionManager.getRates() == null) {
            mService.fetchRates(object : Callback<List<Rate>> {

                override fun onFailure(call: Call<List<Rate>>, t: Throwable) {
                }

                override fun onResponse(call: Call<List<Rate>>, response: Response<List<Rate>>) {
                    if (response.isSuccessful) {

                        TransactionManager.setRates(
                            response.body()!!.mapTo(
                                listOf<RateItem>().toMutableList(),
                                fun (item) : RateItem = RateItem(item)
                            )
                        )
                    }
                }
            })
        }
    }
}