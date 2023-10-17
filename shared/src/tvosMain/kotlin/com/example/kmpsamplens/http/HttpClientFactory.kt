package com.example.kmpsamplens.http

import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.engine.darwin.DarwinClientEngineConfig
import platform.Foundation.NSURLSessionAuthChallengeDisposition
import platform.Foundation.NSURLSessionDelegateProtocol

class HttpClientFactory {

    fun create(challengeHandlerProtocol: NSURLSessionDelegateProtocol): HttpClientWrapper {
        return createHttpClient {
            handleChallenge { session, _, challenge, completionHandler ->
                challengeHandlerProtocol.URLSession(
                    session,
                    challenge
                ) { disposition: NSURLSessionAuthChallengeDisposition, credential ->
                    completionHandler(disposition, credential)
                }
            }
        }
    }

    private fun createHttpClient(clientBuilder: DarwinClientEngineConfig.() -> Unit) = HttpClientWrapper(
        httpClientWithJson(Darwin) {
            engine {
                clientBuilder()
            }
        }
    )
}