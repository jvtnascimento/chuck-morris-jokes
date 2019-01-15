package com.jvtnascimento.chucknorrisjokes.view

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.jvtnascimento.chucknorrisjokes.R
import com.jvtnascimento.chucknorrisjokes.models.Joke
import com.jvtnascimento.chucknorrisjokes.presenter.JokePresenter
import com.jvtnascimento.chucknorrisjokes.services.modules.GlideApp
import com.jvtnascimento.chucknorrisjokes.view.contracts.ViewContractInterface

class JokeActivity : AppCompatActivity(), ViewContractInterface {

    private lateinit var mainContent: LinearLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var jokeIcon: ImageView
    private lateinit var jokeValue: TextView
    private lateinit var nextButton: Button
    private lateinit var linkButton: Button

    private var category: String = ""
    private var joke: Joke? = null

    private var presenter: JokePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joke)

        this.presenter = JokePresenter(this)

        if (intent.hasExtra("category"))
            category = intent.getStringExtra("category")

        this.configureComponents()
        this.loadData()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun showError(error: Throwable) {
        this.hideProgressBar()
    }

    override fun showJoke(joke: Joke) {
        this.joke = joke
        this.hideProgressBar()
        this.configureView()
    }

    private fun loadData() {
        if (this.category != "") {
            this.showProgressBar()
            this.presenter!!.getJoke(this.category)
        } else {
            this.hideProgressBar()
        }
    }

    private fun configureComponents() {
        (this as AppCompatActivity).supportActionBar!!.title = "$category Joke".capitalize()
        (this as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (this as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)

        this.progressBar = findViewById(R.id.progressBar)
        this.mainContent = findViewById(R.id.mainContent)
        this.jokeIcon = findViewById(R.id.jokeIcon)
        this.jokeValue = findViewById(R.id.jokeValue)
        this.nextButton = findViewById(R.id.nextButton)
        this.linkButton = findViewById(R.id.linkButton)

        this.nextButton.setOnClickListener {
            this.loadData()
        }

        this.linkButton.setOnClickListener {
            this.onLinkButtonTap(this.joke)
        }
    }

    private fun configureView() {
        if (this.joke != null) {
            this.jokeValue.text = this.joke!!.value

            GlideApp.with(this)
                .load(this.joke!!.icon_url)
                .placeholder(R.color.imageViewBackground)
                .into(this.jokeIcon)
        }
    }

    private fun showProgressBar() {
        this.mainContent.visibility = View.GONE
        this.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        this.progressBar.visibility = View.GONE
        this.mainContent.visibility = View.VISIBLE
    }

    private fun onLinkButtonTap(joke: Joke?) {
        if (joke != null) {
            val bundle = Bundle()
            bundle.putBoolean("new_window", true)

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(joke.url))
            intent.putExtras(bundle)
            startActivity(intent)
        } else {
            Toast.makeText(
                this,
                "It wasn't possible to get a joke =(",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
