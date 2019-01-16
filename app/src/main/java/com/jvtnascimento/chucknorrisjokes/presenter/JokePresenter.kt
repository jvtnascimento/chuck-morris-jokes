package com.jvtnascimento.chucknorrisjokes.presenter

import com.jvtnascimento.chucknorrisjokes.models.Joke
import com.jvtnascimento.chucknorrisjokes.presenter.contracts.PresenterContractInterface
import com.jvtnascimento.chucknorrisjokes.services.api.ApiServiceInterface
import com.jvtnascimento.chucknorrisjokes.view.contracts.ViewContractInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class JokePresenter: PresenterContractInterface {

    private lateinit var view: ViewContractInterface

    override fun attach(view: ViewContractInterface) {
        this.view = view
    }

    override fun getCategories() {
        ApiServiceInterface
            .create()
            .getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list: ArrayList<String> ->
                this.view.showCategories(list)
            }, { error ->
                error.printStackTrace()
                this.view.showError(error)
            })
    }

    override fun getJoke(category: String) {
        ApiServiceInterface
            .create()
            .getJoke(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result: Joke ->
                this.view.showJoke(result)
            }, { error ->
                error.printStackTrace()
                this.view.showError(error)
            })
    }
}