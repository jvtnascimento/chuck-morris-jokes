package com.jvtnascimento.chucknorrisjokes.application

import android.app.Application
import com.jvtnascimento.chucknorrisjokes.services.APISettings

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        this.configureAPISettings()
    }

    private fun configureAPISettings() {
        APISettings.instance =  APISettings("https", "api.chucknorris.io", 0)
    }
}