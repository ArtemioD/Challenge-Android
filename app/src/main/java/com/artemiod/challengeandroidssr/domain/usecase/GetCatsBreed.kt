package com.artemiod.challengeandroidssr.domain.usecase

import com.artemiod.challengeandroidssr.data.CatsRepositoryImp
import com.artemiod.challengeandroidssr.domain.model.CatItem

class GetCatsBreed {

    private val repository = CatsRepositoryImp()

    suspend fun execute(url: String): List<CatItem> {
        return repository.getCatsBreeds(url)
    }
}