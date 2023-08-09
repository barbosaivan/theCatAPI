package com.example.thecatapi.model

import android.net.Uri
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.thecatapi.BreedsApplication
import com.example.thecatapi.common.entities.Dogs
import com.example.thecatapi.common.utils.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/* 
* Project: TheCatAPI
* From: com.example.thecatapi.model
* Create by Ivan Barbosa on 11/05/2023 at 6:03 p. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/

class DogModel {
    fun requestApi(page: String, callback: (MutableList<Dogs>) -> Unit) {
        val urlBuilder = Uri.parse("${Constants.BASE_URL_DOG}${Constants.API_PATH_CATS}").buildUpon()
        urlBuilder.appendQueryParameter(Constants.API_PATH_LIMIT, Constants.API_LIMIT.toString())
        urlBuilder.appendQueryParameter(Constants.API_PATH_KEY, Constants.API_KEY)
        urlBuilder.appendQueryParameter(Constants.API_PATH_PAGE, page)
        val url = urlBuilder.build().toString()
        Log.i("Url", url)
        val request = object : StringRequest(Method.GET, url, Response.Listener { response ->
            val listDogs: MutableList<Dogs>
            val data = object : TypeToken<MutableList<Dogs>>() {}.type
            listDogs = Gson().fromJson(response, data)
            callback(listDogs)
        }, Response.ErrorListener {
            it.printStackTrace()
        }) {}
        BreedsApplication.apiBreeds.addToRequestQueue(request)
    }
}