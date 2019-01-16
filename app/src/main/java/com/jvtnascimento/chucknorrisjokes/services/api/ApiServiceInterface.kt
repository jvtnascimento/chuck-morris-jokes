package com.jvtnascimento.chucknorrisjokes.services.api

import com.jvtnascimento.chucknorrisjokes.models.Joke
import com.jvtnascimento.chucknorrisjokes.services.Constants
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.ArrayList

interface ApiServiceInterface {

    @GET("jokes/categories")
    fun getCategories(): Observable<ArrayList<String>>

    @GET("jokes/random")
    fun getJoke(@Query("category") category: String): Observable<Joke>

    companion object Factory {
        fun create(): ApiServiceInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}