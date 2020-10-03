package io.huggler.balloonmail.infrastructure

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PingController {

    @GetMapping("/public/ping")
    fun ping(): Map<String, String> {
        val response = HashMap<String, String>()
        response["message"] = "ping"

        return response
    }
}
