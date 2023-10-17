package com.example.kmpsamplens.http

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private fun <T : HttpClientEngineConfig> HttpClientConfig<T>.defaultJsonParsingConfiguration() {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            ignoreUnknownKeys = true
            isLenient = true
        })
    }
}

fun <T : HttpClientEngineConfig> httpClientWithJson(
    engineFactory: HttpClientEngineFactory<T>,
    block: HttpClientConfig<T>.() -> Unit = {}
): HttpClient {
    return HttpClient(engineFactory) {
        defaultJsonParsingConfiguration()
        block()
    }
}

fun httpClientWithJson(
    block: HttpClientConfig<*>.() -> Unit = {}
): HttpClient = HttpClient {
    defaultJsonParsingConfiguration()
    block()
}

class HttpClientWrapper(val httpClient: HttpClient)