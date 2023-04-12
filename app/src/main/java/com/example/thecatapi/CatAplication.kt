package com.example.thecatapi

import android.app.Application
import com.example.thecatapi.common.data.ApiCats

/* 
* Project: TheCatAPI
* From: com.example.thecatapi
* Create by Ivan Barbosa on 12/04/2023 at 4:25 p. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/

class CatApplication: Application() {
    companion object{
        lateinit var apiCats: ApiCats
    }

    override fun onCreate() {
        super.onCreate()
        //Volley
        apiCats = ApiCats.getInstance(this)
    }

}