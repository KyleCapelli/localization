package com.capelli.localization.domain.command

interface Command

data class LocalizationCommand(
    val sourceLanguage: String,
    val targetLanguage: String,
    val content: String
) : Command
