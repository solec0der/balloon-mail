package io.huggler.balloonmail.service

import org.springframework.stereotype.Service

@Service
class UserService {

//    fun getLoggedInUser() {
//        val securityContext = SecurityContextHolder.getContext()
//        val authentication = securityContext.authentication
//
//        if (authentication.principal is KeycloakPrincipal<*>) {
//            val principal = authentication.principal as KeycloakPrincipal<*>
//            val token = principal.keycloakSecurityContext.token
//            return createUserFromAccessToken(token)
//        }
//        throw UserNotLoggedInException()
//    }

//    private fun createUserFromAccessToken(accessToken: AccessToken): UserDTO {
//        return UserDTO(
//                userId = accessToken.subject,
//                preferredUsername = accessToken.preferredUsername,
//                email = accessToken.email
//        )
//    }
}
