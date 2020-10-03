package io.huggler.balloonmail.config

import io.huggler.balloonmail.common.FileLoaderUtil
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.models.security.OAuthFlow
import io.swagger.v3.oas.models.security.OAuthFlows
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springdoc.core.GroupedOpenApi
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@OpenAPIDefinition(info = Info(
        title = "Balloon-Mail Private API Documentation"
))
class PrivateOpenApiConfiguration {

    @Value("\${keycloak.auth-server-url:}")
    private val authServerUrl: String = ""

    @Value("\${keycloak.realm:}")
    private val realm: String = ""

    @Value("\${info.app.version:?}")
    private val version: String = ""

    private val title = "Balloon-Mail Private API"
    private val basePackage = "io.huggler.balloonmail"

    @Bean
    fun privateApi(): GroupedOpenApi {
        val description = FileLoaderUtil.getResourceFileAsString("public-api/documentation/private/description.txt")

        val oAuthFlow = OAuthFlow()
                .authorizationUrl("$authServerUrl/realms/$realm/protocol/openid-connect/auth/?nonce=Joanfe")
                .tokenUrl("$authServerUrl/realms/$realm/protocol/openid-connect/token?nonce=Jofnui3nNiel")

        val oAuthFlows = OAuthFlows().implicit(oAuthFlow)

        val securityScheme = SecurityScheme()
        securityScheme.type = SecurityScheme.Type.OAUTH2
        securityScheme.flows = oAuthFlows

        val securityRequirement = SecurityRequirement()
        securityRequirement.addList("Keycloak")

        return GroupedOpenApi.builder()
                .setGroup("private")
                .addOpenApiCustomiser {
                    it.info.title = title
                    it.info.version = version
                    it.info.description = description
                    it.schemaRequirement("Keycloak", securityScheme)
                    it.addSecurityItem(securityRequirement)
                }
                .packagesToScan("$basePackage.infrastructure", "$basePackage.dto")
                .build()
    }

    @Bean
    fun webMvcConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
                registry.addResourceHandler("/public-api/**").addResourceLocations("classpath:/public-api/")
                registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/favicon.ico")
            }
        }
    }
}
