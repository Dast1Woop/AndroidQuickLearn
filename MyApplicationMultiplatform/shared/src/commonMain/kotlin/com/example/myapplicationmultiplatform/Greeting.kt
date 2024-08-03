package com.example.myapplicationmultiplatform

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hello111, ${platform.name}!"
    }
}