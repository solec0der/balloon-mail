package io.huggler.balloonmail.service

import io.huggler.balloonmail.domain.repository.SettingKeyRepository
import io.huggler.balloonmail.dto.SettingKeyDTO
import io.huggler.balloonmail.service.converter.SettingConverter
import org.springframework.stereotype.Service

@Service
class SettingService(private val settingKeyRepository: SettingKeyRepository) {

    fun getSettings(): List<SettingKeyDTO> {
        val settingKeys = settingKeyRepository.findAllByUserId("jioejiogjreiotjeior")

        return settingKeys
                .map { SettingConverter.convertSettingKeyToDto(it) }
                .toCollection(arrayListOf())
    }
}
