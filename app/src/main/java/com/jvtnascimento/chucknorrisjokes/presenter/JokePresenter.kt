package com.jvtnascimento.chucknorrisjokes.presenter

import com.jvtnascimento.chucknorrisjokes.models.Joke
import com.jvtnascimento.chucknorrisjokes.presenter.contracts.PresenterContractInterface
import com.jvtnascimento.chucknorrisjokes.services.api.ApiServiceInterface
import com.jvtnascimento.chucknorrisjokes.view.contracts.ViewContractInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class JokePresenter: PresenterContractInterface {

    lateinit var view: ViewContractInterface

    override fun attach(view: ViewContractInterface) {
        this.view = view
    }

    override fun getCategories() {
        this.view.showProgressBar()
        ApiServiceInterface
            .create()
            .getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list: ArrayList<String> ->
                this.view.hideProgressBar()
                this.view.showCategories(list)
            }, { error ->
                error.printStackTrace()
                this.view.hideProgressBar()
                this.view.showError(error)
            })
    }

    override fun getJoke(category: String) {
        this.view.showProgressBar()
        ApiServiceInterface
            .create()
            .getJoke(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result: Joke ->
                this.view.hideProgressBar()
                this.view.showJoke(result)
            }, { error ->
                error.printStackTrace()
                this.view.hideProgressBar()
                this.view.showError(error)
            })
    }
}