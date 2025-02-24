package com.capelli.localization.domain.command

import com.capelli.localization.domain.port.TranslateService
import com.capelli.localization.domain.model.TranslatedContent
import org.springframework.stereotype.Service

interface CommandHandler<C : Command, R> {
    fun handle(command: C): R
}

@Service
class LocalizationCommandHandler(
    private val translationService: TranslateService
) : CommandHandler<LocalizationCommand, TranslatedContent> {
    override fun handle(command: LocalizationCommand): TranslatedContent {
        val translatedContent = translationService.translate(
            text = command.content,
            sourceLanguage = command.sourceLanguage,
            targetLanguage = command.targetLanguage
        )

        return TranslatedContent(
            translatedContent = translatedContent,
        )
    }
}
