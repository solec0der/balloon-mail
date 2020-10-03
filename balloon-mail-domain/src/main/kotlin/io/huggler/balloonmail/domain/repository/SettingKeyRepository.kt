package io.huggler.balloonmail.domain.repository

import io.huggler.balloonmail.domain.model.SettingKey
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface SettingKeyRepository : MongoRepository<SettingKey, String> {

    @Query("{'settingValues.userId': ?0}")
    fun findAllByUserId(userId: String): List<SettingKey>
}
