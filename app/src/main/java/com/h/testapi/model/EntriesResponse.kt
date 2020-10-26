package com.h.testapi.model

import com.google.gson.annotations.SerializedName

data class EntriesResponse (
    @SerializedName("SerializedName")
    val status: String,
    @SerializedName("data")
    val data: List<List<Entry>>,
    @SerializedName("error")
    val error: String
)