package io.huggler.balloonmail.security.client

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder
import org.keycloak.OAuth2Constants
import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.KeycloakBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service

@Service
class KeycloakAdminClientFactory(
        @Value("\${keycloak.auth-server-url}")
        private val authServerUrl: String,
        @Value("\${keycloak.realm}")
        private val realm: String,
        @Value("\${keycloak.admin.client-id}")
        private val clientId: String,
        @Value("\${keycloak.admin.client-secret}")
        private val clientSecret: String
) {

    @Bean
    fun createKeycloakAdminClient(): Keycloak {
        return KeycloakBuilder.builder()
                .serverUrl(authServerUrl)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .realm(realm)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .resteasyClient(
                        ResteasyClientBuilder()
                                .connectionPoolSize(10).build()
                ).build()
    }
}
