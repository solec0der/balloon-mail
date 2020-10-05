package io.huggler.balloonmail.domain.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user")
data class User(
        @Id
        val userId: String,
        val preferredUsername: String,
        val email: String
)
