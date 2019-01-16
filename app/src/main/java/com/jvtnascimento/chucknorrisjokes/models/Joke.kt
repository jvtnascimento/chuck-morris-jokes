package com.jvtnascimento.chucknorrisjokes.models

data class Joke (
    var id: String,
    var category: ArrayList<String>,
    var icon_url: String,
    var url: String,
    var value: String
)