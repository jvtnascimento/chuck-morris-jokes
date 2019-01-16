package com.jvtnascimento.chucknorrisjokes.presenter.contracts

import com.jvtnascimento.chucknorrisjokes.view.contracts.ViewContractInterface

interface PresenterContractInterface {
    fun getCategories()
    fun getJoke(category: String)
    fun attach(view: ViewContractInterface)
}