package com.example.thecatapi

import android.app.Application
import com.example.thecatapi.common.data.ApiBreeds
import com.example.thecatapi.common.entities.Cats

/* 
* Project: TheCatAPI
* From: com.example.thecatapi
* Create by Ivan Barbosa on 12/04/2023 at 4:25 p. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/

class BreedsApplication : Application() {
    companion object {
        lateinit var apiBreeds: ApiBreeds
        var loadCats: MutableList<Cats>? = null
    }

    override fun onCreate() {
        super.onCreate()
        //Volley
        apiBreeds = ApiBreeds.getInstance(this)
    }

}