package com.artemiod.challengeandroidssr.domain.usecase

import com.artemiod.challengeandroidssr.data.CatsRepositoryImp
import com.artemiod.challengeandroidssr.domain.model.CatItem

class GetCatsRandom {
    private val repository = CatsRepositoryImp()

    suspend fun execute(): List<CatItem> {
        return repository.getCatsRandom()
    }
}