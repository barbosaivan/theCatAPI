package com.example.thecatapi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thecatapi.common.entities.Cats
import com.example.thecatapi.model.CatModel

/* 
* Project: TheCatAPI
* From: com.example.thecatapi.mainModule.viewModel
* Create by Ivan Barbosa on 12/04/2023 at 3:55 p. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/

class CatViewModel : ViewModel() {
    private var catList: MutableList<Cats>
    private var catModel: CatModel

    init {
        catList = mutableListOf()
        catModel = CatModel()
    }

    private val cats: MutableLiveData<MutableList<Cats>> by lazy {
        MutableLiveData<MutableList<Cats>>()
    }

    fun getCats(): LiveData<MutableList<Cats>> {
        return cats.also { loadCats() }
    }

    private fun loadCats() {
        catModel.requestApi {
            cats.value = it
            catList = it
        }
    }

}