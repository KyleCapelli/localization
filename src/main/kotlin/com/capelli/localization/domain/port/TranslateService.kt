package com.capelli.localization.domain.port

interface TranslateService {
    fun translate(text: String, sourceLanguage: String, targetLanguage: String): String
}
