package com.capelli.localization.api

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class LocalizationCommandControllerTest(@Autowired val mockMvc: MockMvc) {

    @Test
    fun `given a request for localization of recipe Hello world, then return 200 isOk`() {
        val jsonRequest = """
            {
                "sourceLanguage": "en",
                "targetLanguage": "es",
                "content": "Hello world"
            }
        """.trimIndent()

        mockMvc.post("/api/v1/localization/translate") {
            contentType = MediaType.APPLICATION_JSON
            content = jsonRequest
        }
            .andExpect {
                status { isOk() }
                jsonPath("$.translatedContent") { value("Hola mundo") }
            }
    }
}
