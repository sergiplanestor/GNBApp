package bemobile.splanes.com.gnbapp.feature.transaction.model

import android.os.Parcel
import android.os.Parcelable

data class TransactionItem(var sku: String,
                           var amount: String,
                           var currency: CurrencyType) : Parcelable {

    constructor(transaction: Transaction) : this(
        transaction.sku,
        transaction.amount,
        transaction.currency
    )

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        CurrencyType.valueOf(parcel.readString()!!)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(sku)
        parcel.writeString(amount)
        parcel.writeString(currency.value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Transaction> {
        override fun createFromParcel(parcel: Parcel): Transaction {
            return Transaction(parcel)
        }

        override fun newArray(size: Int): Array<Transaction?> {
            return arrayOfNulls(size)
        }
    }
}