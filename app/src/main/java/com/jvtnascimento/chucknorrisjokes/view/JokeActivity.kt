package com.jvtnascimento.chucknorrisjokes.view

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import butterknife.BindView
import butterknife.ButterKnife
import com.jvtnascimento.chucknorrisjokes.R
import com.jvtnascimento.chucknorrisjokes.application.BaseApplication
import com.jvtnascimento.chucknorrisjokes.models.Joke
import com.jvtnascimento.chucknorrisjokes.presenter.JokePresenter
import com.jvtnascimento.chucknorrisjokes.services.modules.GlideApp
import com.jvtnascimento.chucknorrisjokes.view.contracts.ViewContractInterface
import javax.inject.Inject

class JokeActivity : AppCompatActivity(), ViewContractInterface {

    @BindView(R.id.mainContent) lateinit var mainContent: LinearLayout
    @BindView(R.id.progressBar) lateinit var progressBar: ProgressBar
    @BindView(R.id.jokeIcon) lateinit var jokeIcon: ImageView
    @BindView(R.id.jokeValue) lateinit var jokeValue: TextView
    @BindView(R.id.nextButton) lateinit var nextButton: Button
    @BindView(R.id.linkButton) lateinit var linkButton: Button

    @Inject lateinit var presenter: JokePresenter

    private var category: String = ""
    private var joke: Joke? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joke)

        ButterKnife.bind(this)

        if (this.intent.hasExtra("category"))
            this.category = this.intent.getStringExtra("category")

        this.configureComponents()
        this.loadData()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun showError(error: Throwable) {
        Toaster.showMessage("We couldn't get the joke =(", this)
    }

    override fun showJoke(joke: Joke) {
        this.joke = joke
        this.hideProgressBar()
        this.configureView()
    }

    override fun showProgressBar() {
        this.mainContent.visibility = View.GONE
        this.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        this.progressBar.visibility = View.GONE
        this.mainContent.visibility = View.VISIBLE
    }

    private fun loadData() {
        if (this.category != "") {
            this.presenter.getJoke(this.category)
        } else {
            this.hideProgressBar()
        }
    }

    private fun configureComponents() {
        (this as AppCompatActivity).supportActionBar!!.title = "$category Joke".capitalize()
        (this as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (this as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)

        (application as BaseApplication).component.inject(this);
        this.presenter.attach(this)

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

    private fun onLinkButtonTap(joke: Joke?) {
        if (joke != null) {
            val bundle = Bundle()
            bundle.putBoolean("new_window", true)

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(joke.url))
            intent.putExtras(bundle)
            startActivity(intent)
        } else {
            Toaster.showMessage("We couldn't get the joke =(", this)
        }
    }
}
