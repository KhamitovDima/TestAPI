package com.h.testapi.network

import com.h.testapi.model.AddEntryResponse
import com.h.testapi.model.EntriesResponse
import com.h.testapi.model.SessionResponse
import io.reactivex.Single
import retrofit2.http.*

interface Api {

    companion object {
        const val TOKEN = "token: gFSv14S-mN-LUN8dCM"
    }

    @POST(".")
    @Headers(TOKEN)
    @FormUrlEncoded
    fun newSession(@Field("a") newSession: String = "new_session"): Single<SessionResponse>

    @POST(".")
    @Headers(TOKEN)
    @FormUrlEncoded
    fun getEntries(@Field("a") getEntries: String = "get_entries",
                   @Field("session") session: String): Single<EntriesResponse>

    @POST(".")
    @Headers(TOKEN)
    @FormUrlEncoded
    fun addEntry(
        @Field("a") addEntry: String = "add_entry",
        @Field("session") session: String,
        @Field("body") body: String
    ): Single<AddEntryResponse>
}