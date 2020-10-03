package io.huggler.balloonmail.domain.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "settingKeys")
data class SettingKey(
        @Id
        val id: String? = null,
        val key: String,
        val defaultValue: String,
        val settingValues: List<SettingValue>
)
