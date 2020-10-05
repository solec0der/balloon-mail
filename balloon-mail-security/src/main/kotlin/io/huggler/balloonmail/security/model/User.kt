package io.huggler.balloonmail.security.model

data class User(
        val userId: String,
        val preferredUsername: String,
        val email: String
)
