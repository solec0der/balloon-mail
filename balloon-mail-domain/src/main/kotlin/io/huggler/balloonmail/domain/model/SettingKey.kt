package io.huggler.balloonmail.domain.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "settingKeys")
data class SettingKey(
        @Id
        private val id: String? = null,
        private val key: String,
        private val defaultValue: String,
        private val settingValues: List<SettingValue>
)
