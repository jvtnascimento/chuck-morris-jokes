package com.jvtnascimento.chucknorrisjokes.services.retrofit

import io.reactivex.Observable
import retrofit2.http.GET
import java.util.ArrayList

interface CategoryService {
    @GET("jokes/categories")
    fun getCategories(): Observable<ArrayList<String>>
}