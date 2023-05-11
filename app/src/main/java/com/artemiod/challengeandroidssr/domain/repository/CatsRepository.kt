package com.artemiod.challengeandroidssr.domain.repository

import com.artemiod.challengeandroidssr.domain.model.CatItem

interface CatsRepository {

    suspend fun getCatsRandom(): List<CatItem>

    suspend fun getCatsBreeds(urlRaza: String): List<CatItem>
}