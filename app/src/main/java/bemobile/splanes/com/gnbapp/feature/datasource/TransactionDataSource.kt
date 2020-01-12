package bemobile.splanes.com.gnbapp.feature.datasource

import bemobile.splanes.com.gnbapp.feature.model.Rate
import bemobile.splanes.com.gnbapp.feature.model.Transaction
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * Transaction data source.
 */
interface TransactionDataSource {

    @GET(value = "/rates")
    @Headers("Accept: application/json", "charset: UTF-8")
    fun fetchRates() : Call<List<Rate>>

    @GET(value = "/transactions")
    @Headers("Accept: application/json", "charset: UTF-8")
    fun fetchTransactions() : Call<List<Transaction>>
}