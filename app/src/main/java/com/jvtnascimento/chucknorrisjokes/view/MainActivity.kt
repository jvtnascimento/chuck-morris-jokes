package com.jvtnascimento.chucknorrisjokes.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.jvtnascimento.chucknorrisjokes.R
import com.jvtnascimento.chucknorrisjokes.adapters.CategoryAdapter
import com.jvtnascimento.chucknorrisjokes.presenter.JokePresenter
import com.jvtnascimento.chucknorrisjokes.view.contracts.ViewContractInterface

class MainActivity : AppCompatActivity(), ViewContractInterface {

    private var presenter: JokePresenter? = null

    private lateinit var categoryList: RecyclerView
    private lateinit var progressBar: ProgressBar

    private var categories: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.presenter = JokePresenter(this)

        this.configureComponents()
        this.loadData()
    }

    override fun showCategories(categories: ArrayList<String>) {
        this.categories = categories
        configureView()
    }

    override fun showError(error: Throwable) {
        Toast.makeText(
            this,
            "It wasn't possible to get joke categories =(",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun loadData() {
        presenter!!.getCategories()
    }

    private fun configureComponents() {
        (this as AppCompatActivity).supportActionBar!!.title = "Categories"
        this.categoryList = findViewById(R.id.categoryList)
        this.progressBar = findViewById(R.id.progressBar)
    }

    private fun configureView() {
        this.progressBar.visibility = View.GONE
        this.categoryList.visibility = View.VISIBLE

        this.categoryList.layoutManager = LinearLayoutManager(this)
        val adapter = CategoryAdapter(this.categories, this)
        this.categoryList.adapter = adapter
    }
}
