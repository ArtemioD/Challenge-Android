package com.artemiod.challengeandroidssr.data

import com.artemiod.challengeandroidssr.data.model.Cat
import com.artemiod.challengeandroidssr.data.network.ApiService
import com.artemiod.challengeandroidssr.domain.model.CatItem
import com.artemiod.challengeandroidssr.domain.repository.CatsRepository

class CatsRepositoryImp : CatsRepository {

    private val api = ApiService()

    override suspend fun getCatsRandom(): List<CatItem> {
        val response = api.getListCats()
        return response.map { it.toDomain() }
    }

    override suspend fun getCatsBreeds(urlRaza: String): List<CatItem> {
        val response = api.getListCatsBreed(urlRaza)
        return response.map { it.toDomain() }
    }

    private fun Cat.toDomain() : CatItem {
        val id = id
        val img = url_img
        return CatItem(id = id, img = img)
    }
}