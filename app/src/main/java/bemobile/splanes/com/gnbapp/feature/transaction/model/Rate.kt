package bemobile.splanes.com.gnbapp.feature.transaction.model

import android.os.Parcel
import android.os.Parcelable

data class Rate(var from: CurrencyType, var to: CurrencyType, var rate: String) : Parcelable {

    constructor(parcel: Parcel) : this(
        CurrencyType.valueOf(parcel.readString()!!),
        CurrencyType.valueOf(parcel.readString()!!),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(from.value)
        parcel.writeString(to.value)
        parcel.writeString(rate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Rate> {
        override fun createFromParcel(parcel: Parcel): Rate {
            return Rate(parcel)
        }

        override fun newArray(size: Int): Array<Rate?> {
            return arrayOfNulls(size)
        }
    }
}