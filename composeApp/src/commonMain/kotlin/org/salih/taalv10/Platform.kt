package org.salih.taalv10

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun hideLoadingBar()
    
expect fun speak(text: String, lang: String = "nl-NL")

expect suspend fun loadJsonFile(path: String): String