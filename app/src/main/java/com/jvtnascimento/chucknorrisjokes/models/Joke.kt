package com.jvtnascimento.chucknorrisjokes.models

class Joke {
    var id: String = ""
    lateinit var category: Category
    var icon_url: String = ""
    var url: String = ""
    var value: String = ""
}