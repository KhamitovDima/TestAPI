package com.h.testapi.model

data class AddEntryResponse(
    val status: String,
    val data: Id,
    val error: String
)

class Id(val id: String)