package com.capelli.localization.api

import com.capelli.localization.domain.command.CommandHandler
import com.capelli.localization.domain.command.LocalizationCommand
import com.capelli.localization.domain.model.TranslatedContent
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/localization")
class LocalizationCommandController(
    private val localizationCommandHandler: CommandHandler<LocalizationCommand, TranslatedContent>
) {
    @PostMapping("/translate")
    fun translate(@RequestBody request: LocalizationRequest): TranslatedContentResponse {
        val command = LocalizationCommand(
            sourceLanguage = request.sourceLanguage,
            targetLanguage = request.targetLanguage,
            content = request.content
        )
        val translatedRecipe = localizationCommandHandler.handle(command)
        return TranslatedContentResponse(
            translatedContent = translatedRecipe.translatedContent,
        )
    }
}
