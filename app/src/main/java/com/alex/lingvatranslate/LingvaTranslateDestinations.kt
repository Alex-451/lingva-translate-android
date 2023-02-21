package com.alex.lingvatranslate

interface Destination {
    val route: String
}

object Translate : Destination {
    override val route = "translate"
}

object Languages : Destination {
    override val route = "languages"
}