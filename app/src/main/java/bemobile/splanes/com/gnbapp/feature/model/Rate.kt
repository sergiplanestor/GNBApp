package bemobile.splanes.com.gnbapp.feature.model

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

    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of [.writeToParcel],
     * the return value of this method must include the
     * [.CONTENTS_FILE_DESCRIPTOR] bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     */
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