package bemobile.splanes.com.gnbapp.feature

import bemobile.splanes.com.gnbapp.commons.util.MathUtils
import bemobile.splanes.com.gnbapp.feature.model.*

object TransactionManager {

    private var transactionsMap : HashMap<ProductItem, MutableList<TransactionItem>>? = null

    private var rates: List<RateItem>? = null

    fun mapProductTransactions(transactions: MutableList<TransactionItem>) {
        var found : Boolean
        transactionsMap = HashMap()

        for(transaction : TransactionItem in transactions) {
            found = false
            for(product : ProductItem in transactionsMap!!.keys) {
                if (transaction.sku == product.name) {
                    transactionsMap!![product]?.add(transaction)
                    found = true
                    break
                }
            }
            if (!found) {
                transactionsMap!![ProductItem(transaction.sku)] = listOf(transaction).toMutableList()
            }
        }
    }

    fun getProducts() : List<ProductItem>? {
        return transactionsMap?.keys?.toList()
    }

    fun getTransactions(product: ProductItem, currencyType: CurrencyType) : List<TransactionItem>? {

        if (rates == null || transactionsMap == null) return null

        val result = listOf<TransactionItem>().toMutableList()
        val transactions = transactionsMap?.get(product) ?: return null

        for (transaction : TransactionItem in transactions) {
            val transactionClone = TransactionItem(
                transaction.sku,
                transaction.amount,
                currencyType
            )
            if (transaction.currency != currencyType) {
                val ratesFound = getRecursiveConversion(transaction.currency, currencyType)
                for (rate : RateItem in ratesFound) {
                    transactionClone.amount = MathUtils.multiply(transactionClone.amount, rate.rate)
                }
                result.add(transactionClone)

            } else {
                transactionClone.amount = MathUtils.round(transactionClone.amount)
                result.add(transactionClone)
            }
        }

        return result
    }

    private fun getDirectConversion(fromCurrencyType: CurrencyType, toCurrencyType: CurrencyType) : RateItem? {
        for (rate : RateItem in rates!!) {
            if (fromCurrencyType == rate.from && toCurrencyType == rate.to) {
                return rate
            }
        }
        return null
    }

    private fun getRecursiveConversion(fromCurrencyType: CurrencyType,
                                       toCurrencyType: CurrencyType) : MutableList<RateItem> {

        val list = listOf<RateItem>().toMutableList()
        // Checks if exists direct conversion
        val directRate = getDirectConversion(fromCurrencyType, toCurrencyType)
        // In case of not exist direct conversion
        if (directRate == null) {
            for (rate: RateItem in rates!!) {
                if (rate.from == fromCurrencyType && rate.to != toCurrencyType) {
                    list.add(rate)
                    list.addAll(getRecursiveConversion(rate.to, toCurrencyType))
                    break
                }
            }
            return list
        }
        return listOf(directRate).toMutableList()
    }

    fun getRates() : List<RateItem>? {
        return rates
    }

    fun setRates(rates: List<RateItem>) {
        this.rates = rates
    }
}