package com.example.kmpsamplens

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform