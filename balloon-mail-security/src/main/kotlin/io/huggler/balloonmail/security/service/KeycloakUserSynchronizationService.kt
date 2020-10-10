package io.huggler.balloonmail.security.service

import io.huggler.balloonmail.domain.model.User
import io.huggler.balloonmail.domain.repository.UserRepository
import org.keycloak.admin.client.Keycloak
import org.keycloak.representations.idm.UserRepresentation
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class KeycloakUserSynchronizationService(
        private val userRepository: UserRepository,
        private val keycloak: Keycloak,

        @Value("\${keycloak.realm}")
        private val realm: String
) {

    @Scheduled(fixedDelay = 60000)
    fun synchronizeUsers() {
        val localUsers = this.userRepository.findAll()
        val keycloakUsers = keycloak.realm(realm).users().list()

        keycloakUsers.forEach { keycloakUser ->
            val localUser = localUsers.find { localUser -> localUser.userId == keycloakUser.id }

            if (localUser == null) {
                createLocalUserFromKeycloakUser(keycloakUser)
            } else {
                updateLocalUserFromKeycloakUser(localUser, keycloakUser)
            }
        }

        localUsers.forEach {localUser ->
            if (!keycloakUsers.any { keycloakUser -> keycloakUser.id == localUser.userId }) {
                deleteLocalUserByUserId(localUser.userId)
            }
        }
    }

    private fun createLocalUserFromKeycloakUser(keycloakUser: UserRepresentation) {
        val localUser = User(
                userId = keycloakUser.id,
                email = keycloakUser.email,
                firstName = keycloakUser.firstName,
                lastName = keycloakUser.lastName
        )

        userRepository.save(localUser)
    }

    private fun updateLocalUserFromKeycloakUser(localUser: User, keycloakUser: UserRepresentation) {
        val updatedLocalUser = localUser.copy(
                firstName = keycloakUser.firstName,
                lastName = keycloakUser.lastName
        )

        userRepository.save(updatedLocalUser)
    }

    private fun deleteLocalUserByUserId(userId: String) {
        userRepository.deleteById(userId)
    }
}
