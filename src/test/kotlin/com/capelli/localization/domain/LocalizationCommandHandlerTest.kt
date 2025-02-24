package com.capelli.localization.domain

import com.capelli.localization.domain.command.LocalizationCommand
import com.capelli.localization.domain.command.LocalizationCommandHandler
import com.capelli.localization.domain.model.TranslatedContent
import com.capelli.localization.infrastructure.MyMemoryTranslateService
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class LocalizationCommandHandlerTest {
    private val translateService = MyMemoryTranslateService()
    private val commandHandler = LocalizationCommandHandler(translateService)

    @Test
    fun `given some text, when requesting to change the language from english to spanish, then should return correct TranslatedContent`() {
        val command = LocalizationCommand(
            sourceLanguage = "en",
            targetLanguage = "es",
            content = "Hello world"
        )

        val result: TranslatedContent = commandHandler.handle(command)

        val expected = TranslatedContent(
            translatedContent = "Hola mundo",
        )
        assertEquals(expected, result)
    }
}
