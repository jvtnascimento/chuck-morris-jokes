package com.jvtnascimento.chucknorrisjokes.services

class APISettings(var protocol: String, var domain: String, var port: Int) {
    companion object {
        var instance: APISettings? = null
    }

    fun composeBaseURL(): String {
        var url = ""

        if (this.protocol.trim { it <= ' ' } != "" && this.domain.trim { it <= ' ' } != "") {
            url += this.protocol + "://" + this.domain
            if (this.port != 0) {
                url += ":" + this.port
            }
        }

        return url
    }
}