package io.huggler.balloonmail.security.client

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class FeignException(status: HttpStatus, reason: String) : ResponseStatusException(status, reason)

