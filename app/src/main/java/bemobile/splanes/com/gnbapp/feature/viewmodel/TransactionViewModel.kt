package bemobile.splanes.com.gnbapp.feature.viewmodel

import androidx.lifecycle.MutableLiveData
import bemobile.splanes.com.gnbapp.commons.ui.base.BaseViewModel
import bemobile.splanes.com.gnbapp.feature.model.Transaction
import bemobile.splanes.com.gnbapp.feature.service.GNBService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionViewModel constructor(val mService: GNBService) : BaseViewModel() {

    private var transactions: List<Transaction>? = null

    fun getTransactions() : MutableLiveData<List<Transaction>> {

        val mutableLiveData = MutableLiveData<List<Transaction>>()

        if (transactions != null) {
            mutableLiveData.postValue(transactions!!)
        } else {
            mService.fetchTransactions(object : Callback<List<Transaction>> {

                override fun onFailure(call: Call<List<Transaction>>, t: Throwable) {
                    mutableLiveData.postValue(null)
                }

                override fun onResponse(call: Call<List<Transaction>>, response: Response<List<Transaction>>) {
                    if (response.code() == 200 ) {
                        transactions = response.body()!!
                        mutableLiveData.postValue(transactions)
                    } else {
                        mutableLiveData.postValue(null)
                    }

                }
            })
        }

        return mutableLiveData
    }
}