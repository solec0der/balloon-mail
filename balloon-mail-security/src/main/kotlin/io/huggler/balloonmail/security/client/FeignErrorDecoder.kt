package io.huggler.balloonmail.security.client

import com.fasterxml.jackson.databind.ObjectMapper
import feign.Response
import feign.codec.ErrorDecoder
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import java.io.IOException

@Component
class FeignErrorDecoder(
        private val objectMapper: ObjectMapper
) : ErrorDecoder {

    override fun decode(methodKey: String, response: Response): Exception {
        val details = methodKey + ": " + extractErrorMessageFromResponseBody(response.body())

        return FeignException(HttpStatus.valueOf(response.status()), details)
    }

    private fun extractErrorMessageFromResponseBody(responseBody: Response.Body?): String {
        try {
            if  (responseBody == null) {
                return "No response body"
            }

            val response = objectMapper.readValue(responseBody.asInputStream(), Map::class.java)

            return response["message"] as String
        } catch (e: IOException) {
            return "Unable to read response body"
        }
    }
}

