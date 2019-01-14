package com.jvtnascimento.chucknorrisjokes.services.retrofit

import com.jvtnascimento.chucknorrisjokes.models.Joke
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.ArrayList

interface JokeService {
    @GET("jokes/categories")
    fun getCategories(): Observable<ArrayList<String>>

    @GET("jokes/random")
    fun getJoke(@Query("category") category: String): Observable<Joke>
}