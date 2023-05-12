package com.example.thecatapi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thecatapi.common.entities.Cats
import com.example.thecatapi.common.entities.Dogs
import com.example.thecatapi.model.CatModel
import com.example.thecatapi.model.DogModel

/* 
* Project: TheCatAPI
* From: com.example.thecatapi.viewModel
* Create by Ivan Barbosa on 11/05/2023 at 6:03 p. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/

class DogViewModel: ViewModel() {
    private var dogList: MutableList<Dogs>
    private var dogModel: DogModel

    init {
        dogList = mutableListOf()
        dogModel = DogModel()
    }

    private val dogs: MutableLiveData<MutableList<Dogs>> by lazy {
        MutableLiveData<MutableList<Dogs>>()
    }

    fun getDogs(): LiveData<MutableList<Dogs>> {
        return dogs.also { loadDogs() }
    }

    private fun loadDogs() {
        dogModel.requestApi {
            dogs.value = it
            dogList = it
        }
    }
}