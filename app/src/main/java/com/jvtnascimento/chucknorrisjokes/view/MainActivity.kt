package com.jvtnascimento.chucknorrisjokes.view

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.jvtnascimento.chucknorrisjokes.R
import com.jvtnascimento.chucknorrisjokes.adapters.CategoryAdapter
import com.jvtnascimento.chucknorrisjokes.application.BaseApplication
import com.jvtnascimento.chucknorrisjokes.presenter.JokePresenter
import com.jvtnascimento.chucknorrisjokes.view.contracts.ViewContractInterface
import java.net.ConnectException
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ViewContractInterface {

    @BindView(R.id.categoryList) lateinit var categoryList: RecyclerView
    @BindView(R.id.progressBar) lateinit var progressBar: ProgressBar

    @Inject lateinit var presenter: JokePresenter

    private var categories: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButterKnife.bind(this)

        this.configureComponents()
        this.loadData()
    }

    override fun showCategories(categories: ArrayList<String>) {
        this.categories = categories
        this.configureView()
    }

    override fun showError(error: Throwable) {
        AlertDialog.Builder(this)
            .setTitle("Oops...")
            .setMessage("We couldn't get the joke categories =(")
            .setCancelable(false)
            .setPositiveButton("Try again"){ _, _ ->
                   this.loadData()
            }
            .show()
    }

    override fun showProgressBar() {
        this.progressBar.visibility = View.VISIBLE
        this.categoryList.visibility = View.GONE
    }

    override fun hideProgressBar() {
        this.progressBar.visibility = View.GONE
        this.categoryList.visibility = View.VISIBLE
    }

    private fun loadData() {
        this.presenter.getCategories()
    }

    private fun configureComponents() {
        (this as AppCompatActivity).supportActionBar!!.title = "Categories"

        (application as BaseApplication).component.inject(this)
        this.presenter.attach(this)
    }

    private fun configureView() {
        this.categoryList.layoutManager = LinearLayoutManager(this)
        this.categoryList.adapter = CategoryAdapter(this.categories, this)
    }

}
