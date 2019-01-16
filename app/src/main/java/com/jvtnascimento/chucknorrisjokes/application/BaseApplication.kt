package com.jvtnascimento.chucknorrisjokes.application

import android.app.Application
import com.jvtnascimento.chucknorrisjokes.dagger.AppComponent
import com.jvtnascimento.chucknorrisjokes.dagger.DaggerAppComponent
import com.jvtnascimento.chucknorrisjokes.dagger.JokePresenterModule

class BaseApplication: Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        this.configureDaggerComponent()
    }

    private fun configureDaggerComponent() {
        this.component = DaggerAppComponent.builder()
            .jokePresenterModule(JokePresenterModule())
            .build()
    }
}