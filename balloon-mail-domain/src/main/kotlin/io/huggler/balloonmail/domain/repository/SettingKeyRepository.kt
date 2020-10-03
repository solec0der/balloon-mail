package io.huggler.balloonmail.domain.repository

import io.huggler.balloonmail.domain.model.SettingKey
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface SettingKeyRepository : MongoRepository<SettingKey, String>
