package org.salih.taalv10

import kotlin.js.Promise
import kotlinx.coroutines.await

class WasmPlatform: Platform {
    override val name: String = "Web with Kotlin/Wasm"
}

actual fun getPlatform(): Platform = WasmPlatform()

@JsFun("() => { if (window.hideLoadingBar) window.hideLoadingBar(); }")
external fun hideLoadingBarInternal()

actual fun hideLoadingBar() {
    hideLoadingBarInternal()
}

@JsFun("(text, lang) => { const utterance = new SpeechSynthesisUtterance(text); utterance.lang = lang; window.speechSynthesis.speak(utterance); }")
external fun speakInternal(text: String, lang: String)

actual fun speak(text: String, lang: String) {
    speakInternal(text, lang)
}

@JsFun("(url) => fetch(url).then(r => r.text())")
external fun fetchTextInternal(url: String): Promise<JsString>

actual suspend fun loadJsonFile(path: String): String {
    val jsString = fetchTextInternal(path).await<JsString>()
    return jsString.toString()
}