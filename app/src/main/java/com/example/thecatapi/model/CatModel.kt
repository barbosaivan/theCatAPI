package com.example.thecatapi.model

import android.net.Uri
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.thecatapi.BreedsApplication
import com.example.thecatapi.common.entities.Cats
import com.example.thecatapi.common.utils.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/* 
* Project: TheCatAPI
* From: com.example.thecatapi.mainModule.model
* Create by Ivan Barbosa on 12/04/2023 at 3:55 p. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/

class CatModel {

    fun requestApi(page: String, callback: (MutableList<Cats>) -> Unit) {
        val urlBuilder = Uri.parse("${Constants.BASE_URL_CAT}${Constants.API_PATH_CATS}").buildUpon()
        urlBuilder.appendQueryParameter(Constants.API_PATH_LIMIT, Constants.API_LIMIT.toString())
        urlBuilder.appendQueryParameter(Constants.API_PATH_KEY, Constants.API_KEY)
        urlBuilder.appendQueryParameter(Constants.API_PATH_PAGE, page)
        val url = urlBuilder.build().toString()
        Log.i("Url", url)
        val request = object : StringRequest(Method.GET, url, Response.Listener { response ->
            val listCats: MutableList<Cats>
            val data = object : TypeToken<MutableList<Cats>>() {}.type
            listCats = Gson().fromJson(response, data)
            callback(listCats)
        }, Response.ErrorListener {
            it.printStackTrace()
        }) {}
        BreedsApplication.apiBreeds.addToRequestQueue(request)
    }
}