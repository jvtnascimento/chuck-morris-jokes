package com.jvtnascimento.chucknorrisjokes.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.jvtnascimento.chucknorrisjokes.R
import com.jvtnascimento.chucknorrisjokes.adapters.CategoryAdapter
import com.jvtnascimento.chucknorrisjokes.services.retrofit.CategoryService
import com.jvtnascimento.chucknorrisjokes.services.retrofit.client.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    var categoryList: ListView? = null

    var categories: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (this as AppCompatActivity).supportActionBar!!.title = "Categories"

        this.categoryList = findViewById(R.id.categoryList)
        this.loadData()
    }

    private fun loadData() {
        RetrofitClient
            .createService(CategoryService::class.java)
            .getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                categories = result
                configureView()
            }, { e ->
                e.printStackTrace()
            })
    }

    private fun configureView() {
        val adapter = CategoryAdapter(this, this.categories)
        this.categoryList!!.adapter = adapter

        this.categoryList!!.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            val category = categories[position]
            Toast.makeText(this, "Click on " + category, Toast.LENGTH_SHORT).show()
        }
    }
}
