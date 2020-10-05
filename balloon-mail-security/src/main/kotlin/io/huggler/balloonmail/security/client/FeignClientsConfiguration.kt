package io.huggler.balloonmail.security.client

import feign.Client
import feign.Contract
import feign.Feign
import feign.Retryer
import feign.codec.Decoder
import feign.codec.Encoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class FeignClientsConfiguration(
        private val feignErrorDecoder: FeignErrorDecoder
) {

    @Bean
    fun getKeycloakAdminClient(): KeycloakAdminClient {
        return createClient("", KeycloakAdminClient::class.java)
    }

    @Bean
    fun feignClient(): Client {
        return Client.Default(null, null)
    }

    private fun <T> createClient(serviceUrl: String, clientClass: Class<T>): T {
        return Feign.builder()
                .client(feignClient()).contract(Contract.Default())
                .encoder(Encoder.Default()).decoder(Decoder.Default())
                .errorDecoder(feignErrorDecoder)
                .retryer(Retryer.NEVER_RETRY)
                .target(clientClass, serviceUrl)
    }
}
