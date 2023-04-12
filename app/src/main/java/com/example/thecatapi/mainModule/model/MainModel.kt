package com.example.thecatapi.mainModule.model

import android.net.Uri
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.thecatapi.CatApplication
import com.example.thecatapi.common.entities.Cats
import com.example.thecatapi.common.utils.Constans
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/* 
* Project: TheCatAPI
* From: com.example.thecatapi.mainModule.model
* Create by Ivan Barbosa on 12/04/2023 at 3:55 p. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/

class MainModel {

    fun requestApi(callback: (MutableList<Cats>) -> Unit) {
        val urlBuilder = Uri.parse("${Constans.BASE_URL}${Constans.API_PATH_CATS}").buildUpon()
        urlBuilder.appendQueryParameter(Constans.API_PATH_KEY, Constans.API_KEY)
        val url = urlBuilder.build().toString()
        val request = object : StringRequest(Method.GET, url, Response.Listener{ response ->
            val listUsers: MutableList<Cats>
            val data = object : TypeToken<MutableList<Cats>>() {}.type
            listUsers = Gson().fromJson(response, data)
            callback(listUsers)
        },
            Response.ErrorListener {
                it.printStackTrace()
            }
        ) {}
        CatApplication.apiCats.addToRequestQueue(request)
    }
}