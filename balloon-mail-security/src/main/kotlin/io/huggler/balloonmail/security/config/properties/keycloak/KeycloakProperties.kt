package io.huggler.balloonmail.security.config.properties.keycloak

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "keycloak")
data class KeycloakProperties(
        var realm: String = "",
        var resource: String = "",
        var sslRequired: String = "",
        var bearerOnly: String = "",
        var authServerUrl: String = "",
        var credentials: Credentials = Credentials("")
)

