package com.jvtnascimento.chucknorrisjokes.services.retrofit

import android.icu.util.ULocale
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import java.util.ArrayList

interface JokeService {
    @GET("jokes/random")
    abstract fun getJoke(@Body category: String): Observable<ArrayList<ULocale.Category>>
}