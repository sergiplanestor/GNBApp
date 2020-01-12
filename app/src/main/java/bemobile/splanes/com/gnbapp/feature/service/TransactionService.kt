package bemobile.splanes.com.gnbapp.feature.service

import bemobile.splanes.com.gnbapp.feature.datasource.TransactionDataSource
import bemobile.splanes.com.gnbapp.feature.model.Rate
import bemobile.splanes.com.gnbapp.feature.model.Transaction
import retrofit2.Callback
import javax.inject.Inject

/**
 * Service responsible for making calls to the webservice via {@link TransactionDataSource}.
 */
class TransactionService
    @Inject
    constructor(private val mDataSource: TransactionDataSource) {

    /**
     * Method to fetch all rates.
     * @param callback {@link retrofit2.Callback} to handle service response
     */
    fun fetchRates(callback: Callback<List<Rate>>) {
        mDataSource.fetchRates().enqueue(callback)
    }

    /**
     * Method to fetch all transactions.
     * @param callback {@link retrofit2.Callback} to handle service response
     */
    fun fetchTransactions(callback: Callback<List<Transaction>>) {
        mDataSource.fetchTransactions().enqueue(callback)
    }

}