package io.huggler.balloonmail.security.service

import io.huggler.balloonmail.domain.repository.UserRepository
import io.huggler.balloonmail.security.model.User
import org.springframework.stereotype.Service

@Service
class KeycloakUserSynchronizationService(
        private val userRepository: UserRepository
) {

    fun synchronizeUsers() {
        val localUsers = this.userRepository.findAll()
        val keycloakUsers = ArrayList<User>()
    }
}
