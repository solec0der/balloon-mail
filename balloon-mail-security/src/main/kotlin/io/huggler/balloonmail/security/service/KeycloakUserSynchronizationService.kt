package io.huggler.balloonmail.security.service

import org.keycloak.admin.client.Keycloak
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class KeycloakUserSynchronizationService(
        private val userService: UserService,
        private val keycloak: Keycloak,

        @Value("\${keycloak.realm}")
        private val realm: String
) {

//    @Scheduled(fixedDelay = 60000)
    fun synchronizeUsers() {
        val localUsers = this.userService.getUsers()
        val keycloakUsers = keycloak.realm(realm).users().list()

        keycloakUsers.forEach { keycloakUser ->
            val localUser = localUsers.find { localUser -> localUser.userId == keycloakUser.id }

            if (localUser == null) {
                userService.createLocalUserFromKeycloakUser(keycloakUser)
            } else {
                userService.updateLocalUserFromKeycloakUser(localUser, keycloakUser)
            }
        }

        localUsers.forEach {localUser ->
            if (!keycloakUsers.any { keycloakUser -> keycloakUser.id == localUser.userId }) {
                userService.deleteLocalUserByUserId(localUser.userId)
            }
        }
    }
}
