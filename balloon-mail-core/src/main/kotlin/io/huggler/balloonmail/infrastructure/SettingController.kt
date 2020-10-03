package io.huggler.balloonmail.infrastructure

import io.huggler.balloonmail.dto.SettingKeyDTO
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/settings")
class SettingController {

    @GetMapping
    @Operation(
            summary = "getSettings",
            description = "returns the settings of the logged in user",
            tags = ["Settings"]
    )
    fun getSettings(): List<SettingKeyDTO> {
        return ArrayList()
    }
}
