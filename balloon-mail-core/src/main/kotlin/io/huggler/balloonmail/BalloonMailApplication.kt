package io.huggler.balloonmail

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BalloonMailApplication

fun main(args: Array<String>) {
	runApplication<BalloonMailApplication>(*args)
}
