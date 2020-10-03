package io.huggler.balloonmail.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "SettingKey", description = "Setting Key")
data class SettingKeyDTO(
        @field:Schema(description = "Unique Identifier")
        private val id: String,

        @field:Schema(description = "Setting Key")
        private val key: String,

        @field:Schema(description = "Default Value that applies, if user doesn't override the setting")
        private val defaultValue: String,

        @field:Schema(description = "Custom Setting Value for user")
        private val settingValue: SettingValueDTO
)
