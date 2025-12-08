package com.kishorramani.navigation3

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform