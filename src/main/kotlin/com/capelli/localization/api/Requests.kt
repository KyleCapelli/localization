package com.capelli.localization.api

data class LocalizationRequest(
    val sourceLanguage: String,
    val targetLanguage: String,
    val content: String
)