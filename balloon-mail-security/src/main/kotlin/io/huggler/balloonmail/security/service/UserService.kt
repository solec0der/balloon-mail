package io.huggler.balloonmail.security.service

import io.huggler.balloonmail.domain.model.User
import io.huggler.balloonmail.domain.repository.UserRepository
import org.keycloak.representations.idm.UserRepresentation
import org.springframework.stereotype.Service

@Service
class UserService(
        private val userRepository: UserRepository
) {

    fun getUsers(): List<User> {
        return userRepository.findAll()
    }

    fun createLocalUserFromKeycloakUser(keycloakUser: UserRepresentation) {
        val localUser = User(
                userId = keycloakUser.id,
                email = keycloakUser.email,
                firstName = keycloakUser.firstName,
                lastName = keycloakUser.lastName
        )

        userRepository.save(localUser)
    }

    fun updateLocalUserFromKeycloakUser(localUser: User, keycloakUser: UserRepresentation) {
        val updatedLocalUser = localUser.copy(
                firstName = keycloakUser.firstName,
                lastName = keycloakUser.lastName
        )

        userRepository.save(updatedLocalUser)
    }

    fun deleteLocalUserByUserId(userId: String) {
        userRepository.deleteById(userId)
    }
}
