package com.pipe.listapplication.data.request

import com.pipe.listapplication.data.models.ServerData
import retrofit2.http.GET
import retrofit2.http.Query

interface CandidateApi {

    @GET("/api/books")
    suspend fun getData(
        @Query("page") page: Int
    ): ServerData
}
