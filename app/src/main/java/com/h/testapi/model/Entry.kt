package com.h.testapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Entry(
    @SerializedName("id")
    val id: String,
    @SerializedName("body")
    val body: String,
    @SerializedName("da")
    val da: Long,
    @SerializedName("dm")
    val dm: Long
) : Parcelable