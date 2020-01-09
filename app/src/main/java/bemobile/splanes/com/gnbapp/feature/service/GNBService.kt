package bemobile.splanes.com.gnbapp.feature.service

import bemobile.splanes.com.gnbapp.feature.datasource.GNBDataSource
import bemobile.splanes.com.gnbapp.feature.model.Rate
import bemobile.splanes.com.gnbapp.feature.model.Transaction
import retrofit2.Callback
import javax.inject.Inject

class GNBService
    @Inject
    constructor(private val mDataSource: GNBDataSource) {

    fun fetchRates(callback: Callback<List<Rate>>) {
        mDataSource.fetchRates().enqueue(callback)
    }

    fun fetchTransactions(callback: Callback<List<Transaction>>) {
        mDataSource.fetchTransactions().enqueue(callback)
    }

}