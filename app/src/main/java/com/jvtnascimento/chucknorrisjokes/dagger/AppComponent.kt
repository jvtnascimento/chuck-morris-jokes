package com.jvtnascimento.chucknorrisjokes.dagger

import com.jvtnascimento.chucknorrisjokes.view.JokeActivity
import com.jvtnascimento.chucknorrisjokes.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [JokePresenterModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(jokeActivity: JokeActivity)
}