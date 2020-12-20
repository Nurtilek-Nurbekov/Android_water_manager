package android.example.watermanager.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Bottle (
    var date: String = "",
    var amount: String = "",
    var photo: Int = 0
): Parcelable