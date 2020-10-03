package io.huggler.balloonmail.service.converter

import io.huggler.balloonmail.domain.model.SettingKey
import io.huggler.balloonmail.dto.SettingKeyDTO

object SettingConverter {

    fun convertSettingKeyToDto(settingKey: SettingKey): SettingKeyDTO {
        return SettingKeyDTO(
                id = settingKey.id ?: "",
                key = settingKey.key,
                defaultValue = settingKey.defaultValue,
                settingValue = if (settingKey.settingValues.isNotEmpty()) settingKey.settingValues[0].value else null
        )
    }
}
