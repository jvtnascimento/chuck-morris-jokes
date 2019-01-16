package com.jvtnascimento.chucknorrisjokes.view.contracts

import com.jvtnascimento.chucknorrisjokes.models.Joke


interface ViewContractInterface {
    fun showCategories(categories: ArrayList<String>) {}
    fun showJoke(joke: Joke) {}
    fun showError(error: Throwable)
}