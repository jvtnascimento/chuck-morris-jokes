package com.jvtnascimento.chucknorrisjokes.services.retrofit.client

import com.jvtnascimento.chucknorrisjokes.services.APISettings
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        var instance: Retrofit? = null

        private fun getRetrofitInstance(): Retrofit {
            if (instance == null) {
                val httpClient = OkHttpClient.Builder()

                instance = Retrofit.Builder()
                    .baseUrl(APISettings.instance!!.composeBaseURL())
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            }
            return instance!!
        }

        fun <T> createService(clazz: Class<T>): T {
            val retrofit = this.getRetrofitInstance()
            return retrofit.create(clazz)
        }
    }
}