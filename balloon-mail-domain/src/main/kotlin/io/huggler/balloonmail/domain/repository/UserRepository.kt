package io.huggler.balloonmail.domain.repository

import io.huggler.balloonmail.domain.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String>
