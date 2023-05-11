package com.artemiod.challengeandroidssr.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemiod.challengeandroidssr.domain.model.CatItem
import com.artemiod.challengeandroidssr.domain.usecase.GetCatsBreed
import com.artemiod.challengeandroidssr.domain.usecase.GetCatsRandom
import kotlinx.coroutines.launch

enum class ApiStatus {LOADING, ERROR, DONE}

class CatViewModel: ViewModel() {

    private val _catList = MutableLiveData<List<CatItem>>()
    val catList: LiveData<List<CatItem>> get() = _catList

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> get() = _status

    init {
        getRandomCats()
    }

    private fun getRandomCats() {
        _status.value = ApiStatus.LOADING
        viewModelScope.launch {
            try {
                _catList.value = GetCatsRandom().execute()
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _catList.value = listOf()
            }
        }
    }

    fun searchCatBreed(raza: String) {
        viewModelScope.launch {
            if (raza.length >= 4) {
                _status.value = ApiStatus.LOADING
                val auxString = raza.lowercase().trimStart().slice(0..3)
                val url = "images/search?limit=10&breed_ids=$auxString"
                try {
                    _catList.value = GetCatsBreed().execute(url)
                    _status.value = ApiStatus.DONE
                } catch (e: Exception) {
                    _status.value = ApiStatus.ERROR
                    _catList.value = listOf()
                }
            }

        }
    }

}