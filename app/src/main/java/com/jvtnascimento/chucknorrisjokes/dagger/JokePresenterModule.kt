package com.jvtnascimento.chucknorrisjokes.dagger

import com.jvtnascimento.chucknorrisjokes.presenter.JokePresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class JokePresenterModule {

    @Provides
    @Singleton
    fun provideJokePresenter(): JokePresenter {
        return JokePresenter()
    }
}