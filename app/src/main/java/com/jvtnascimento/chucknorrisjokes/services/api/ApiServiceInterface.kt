package com.jvtnascimento.chucknorrisjokes.services.api

import com.jvtnascimento.chucknorrisjokes.models.Joke
import com.jvtnascimento.chucknorrisjokes.services.Constants
import io.reactivex.Observable
import retrofit2.Retrofit
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

    companion object {
        var retrofit: Retrofit? = null

        fun create(): ApiServiceInterface {
            if (this.retrofit == null) {
                this.retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(Constants.BASE_URL)
                    .build()
            }

            return this.retrofit!!.create(ApiServiceInterface::class.java)
        }
    }
}