package bemobile.splanes.com.gnbapp.feature.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import bemobile.splanes.com.gnbapp.commons.ui.base.BaseViewModel
import bemobile.splanes.com.gnbapp.feature.TransactionManager
import bemobile.splanes.com.gnbapp.feature.model.*
import bemobile.splanes.com.gnbapp.feature.service.TransactionService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Transaction ViewModel.
 * This class will provide all {@link LiveData} needed to deal with Products and Transactions.
 */
class TransactionViewModel constructor(private val mService: TransactionService) : BaseViewModel() {

    /**
     * Gets transactions of given ProductItem and converted to the specified CurrencyType.
     * @param productItem {@link ProductItem} from which get transactions.
     * @param currencyType {@link CurrencyType} to which want to convert all transactions.
     * @return {@link LiveData} containing List of requested transaction items. See {@link TransactionItem}
     */
    fun getTransactions(productItem: ProductItem, currencyType: CurrencyType) : LiveData<List<TransactionItem>> {

        val liveData = MutableLiveData<List<TransactionItem>>()
        val transactions = TransactionManager.getTransactions(productItem, currencyType)
        if (transactions != null) {
            liveData.postValue(transactions)
        }

        return liveData
    }

    /**
     * Gets all products. This method will call service to get all transactions and rates.
     * @return {@link LiveData} containing List of product items, see {@see ProductItem}. In case
     * of service error, data posted will be null.
     */
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

    /**
     * Gets all rates. Data received will be stored in {@link TransactionManager}.
     */
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