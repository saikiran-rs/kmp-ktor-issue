# Sample project to reproduce KMM Ktor iOS type mismatch

Type Mismatch Issue with NSURLSessionAuthChallengeDisposition in Kotlin Native


## How to reproduce
1. Clone the project
2. Run ./gradlew build
3. Observer the below error


```` > Task :shared:compileIosMainKotlinMetadata FAILED
e: file:///.../shared/src/iosMain/kotlin/com/example/kmpsamplens/http/HttpClientFactory.kt:17:39 Type mismatch: inferred type is NSURLSessionAuthChallengeDisposition /* = Long */ but NSURLSessionAuthChallengeDisposition /* = Int */ was expected
