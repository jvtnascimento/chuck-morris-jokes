package com.jvtnascimento.chucknorrisjokes

import com.jvtnascimento.chucknorrisjokes.presenter.JokePresenter
import com.jvtnascimento.chucknorrisjokes.view.contracts.ViewContractInterface
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Test

import org.junit.Before
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.plugins.RxAndroidPlugins


class ExampleUnitTest {

    @MockK
    lateinit var view: ViewContractInterface

    private lateinit var jokePresenter: JokePresenter
    private val category: String = "dev"

    private val categories: ArrayList<String> = arrayListOf("explicit","dev","movie","food","celebrity","science","sport","political","religion","animal","history","music","travel","career","money","fashion")

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }
        this.jokePresenter = JokePresenter()

    }

    @Test
    fun getCategories_shouldShowCategories() {
        this.jokePresenter.view = this.view
        this.jokePresenter.getCategories()
        //verify(view).showCategories(categories)
    }
}
