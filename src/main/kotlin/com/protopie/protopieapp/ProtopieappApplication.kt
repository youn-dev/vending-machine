package com.protopie.protopieapp

import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*

@SpringBootApplication
class ProtopieappApplication {
    @PostConstruct
    fun started() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"))
        java.security.Security.setProperty("networkaddress.cache.ttl", "0")
    }
}

fun main(args: Array<String>) {
    runApplication<ProtopieappApplication>(*args)
}
