package com.jvtnascimento.chucknorrisjokes.application

import android.app.Application

class BaseApplication: Application() {

    companion object {
        lateinit var instance: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        this.setup()
    }

    fun setup() {
//        component = DaggerApplicationComponent.builder()
//            .applicationModule(ApplicationModule(this)).build()
//        component.inject(this)
    }

}