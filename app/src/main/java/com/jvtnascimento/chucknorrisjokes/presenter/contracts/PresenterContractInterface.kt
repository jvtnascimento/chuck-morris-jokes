package com.jvtnascimento.chucknorrisjokes.presenter.contracts

interface PresenterContractInterface {
    fun getCategories()
    fun getJoke(category: String)
}