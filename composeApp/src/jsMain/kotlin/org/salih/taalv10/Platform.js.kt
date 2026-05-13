package org.salih.taalv10

import kotlinx.browser.window
import kotlinx.coroutines.await

class JsPlatform: Platform {
    override val name: String = "Web with Kotlin/JS"
}

actual fun getPlatform(): Platform = JsPlatform()

actual fun hideLoadingBar() {
    js("if (window.hideLoadingBar) window.hideLoadingBar();")
}

actual fun speak(text: String, lang: String) {
    val utterance = js("new SpeechSynthesisUtterance(text)")
    utterance.lang = lang
    js("window.speechSynthesis.speak(utterance)")
}

actual suspend fun loadJsonFile(path: String): String {
    val response = window.fetch(path).await()
    return response.text().await() as String
}