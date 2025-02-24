package com.capelli.localization.infrastructure

import com.capelli.localization.domain.port.TranslateService
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

data class MyMemoryResponseData(val translatedText: String)
data class MyMemoryResponse(val responseData: MyMemoryResponseData)

@Service
class MyMemoryTranslateService : TranslateService {
    private val webClient = WebClient.builder()
        .baseUrl("https://api.mymemory.translated.net")
        .build()

    override fun translate(text: String, sourceLanguage: String, targetLanguage: String): String {
        return webClient.get()
            .uri { uriBuilder ->
                uriBuilder
                    .path("/get")
                    .queryParam("q", text)
                    .queryParam("langpair", "$sourceLanguage|$targetLanguage")
                    .build()
            }
            .retrieve()
            .bodyToMono(MyMemoryResponse::class.java)
            .map { it.responseData.translatedText }
            .block() ?: throw IllegalStateException("Translation failed")
    }
}
