package bemobile.splanes.com.gnbapp.feature

import bemobile.splanes.com.gnbapp.commons.util.MathUtils
import bemobile.splanes.com.gnbapp.feature.model.*

/**
 * Object manager that provides static methods to deal with transactions. Also is used as repository
 * and store data received from service.
 */
object TransactionManager {

    private var transactionsMap : HashMap<ProductItem, MutableList<TransactionItem>>? = null

    private var rates: List<RateItem>? = null

    /**
     * Method to map each product with its list of transactions made.
     * @param transactions MutableList<TransactionItem> data received from service.
     */
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

    /**
     * Get all products ({@link #transactionsMap} keys)
     */
    fun getProducts() : List<ProductItem>? {
        return transactionsMap?.keys?.toList()
    }

    /**
     * Get all transactions of given product and convert its currency to given currency type.
     * @param product {@link ProductItem} from which get transactions.
     * @param currencyType {@link CurrencyType} to which want to convert all transactions.
     * @return list of transactions item {@see TransactionItem} from given product and
     * converted to given currency type.
     */
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

    /**
     * Private method to get rate item given two currency types.
     * @param fromCurrencyType original currency type
     * @param toCurrencyType target currency type
     * @return rate item {@link RateItem} if exists direct conversion with two given currency types.
     * In case of not exists, null will be returned
     */
    private fun getDirectConversion(fromCurrencyType: CurrencyType, toCurrencyType: CurrencyType) : RateItem? {
        for (rate : RateItem in rates!!) {
            if (fromCurrencyType == rate.from && toCurrencyType == rate.to) {
                return rate
            }
        }
        return null
    }

    /**
     * Private method to get rate items list given two currency types. This method will be called
     * recursively if not exists direct conversion between the given currency types.
     * @param fromCurrencyType original currency type
     * @param toCurrencyType target currency type
     * @return rate items list containing all rates needed to convert from given currency type to
     * given target type.
     */
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

    /**
     * Get all rates
     * @return rate items list
     */
    fun getRates() : List<RateItem>? {
        return rates
    }

    /**
     * Set all rates
     */
    fun setRates(rates: List<RateItem>) {
        this.rates = rates
    }
}