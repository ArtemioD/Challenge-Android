package com.artemiod.challengeandroidssr.data.network

import com.artemiod.challengeandroidssr.data.model.Cat
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiClient {

    @GET(value = "images/search?limit=10")
    suspend fun getCatsRandom(): Response<List<Cat>>

    @GET
    suspend fun getCatBreed(@Url url: String): Response<List<Cat>>
}