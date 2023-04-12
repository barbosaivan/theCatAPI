package com.example.thecatapi.common.data

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley


/* 
* Project: TheCatAPI
* From: com.example.thecatapi.common.data
* Create by Ivan Barbosa on 12/04/2023 at 3:47 p. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/

class ApiCats  constructor(context: Context){
    companion object{
        @Volatile
        private var INSTANCE: ApiCats? = null

        fun getInstance(context: Context) = INSTANCE ?: synchronized(this){
            INSTANCE ?: ApiCats(context).also { INSTANCE = it }
        }
    }

    val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    fun<T> addToRequestQueue(req: Request<T>){
        requestQueue.add(req)
    }
}