package com.artemiod.challengeandroidssr.data.network

import com.artemiod.challengeandroidssr.data.model.Cat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApiService {

    private val retrofit = MyRetrofit.getRetrofit()

    suspend fun getListCats(): List<Cat> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ApiClient::class.java).getCatsRandom()
            response.body() ?: emptyList()
        }
    }

    suspend fun getListCatsBreed(url: String): List<Cat> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ApiClient::class.java).getCatBreed(url)
            response.body() ?: emptyList()
        }
    }
}