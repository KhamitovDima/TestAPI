package com.h.testapi.model

import com.google.gson.annotations.SerializedName

data class SessionResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val data: Session,
    @SerializedName("error")
    val error: String
)