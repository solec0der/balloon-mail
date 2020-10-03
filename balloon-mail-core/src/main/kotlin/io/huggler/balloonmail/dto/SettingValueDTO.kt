package io.huggler.balloonmail.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "SettingValue", description = "Setting Value for a Setting Key")
data class SettingValueDTO(

        @field:Schema(description = "Setting Value")
        private val value: String
)
