package com.raywenderlich.android.networking

interface NetworkConfiguration {
    val cacheSize: Long // 1
    val serverBaseUrl: String // 2
    val dateFormat: String // 3
}