package org.salih.taalv10

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun hideLoadingBar()

expect suspend fun loadJsonFile(path: String): String