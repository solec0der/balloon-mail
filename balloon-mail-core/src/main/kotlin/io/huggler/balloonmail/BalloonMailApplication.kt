package io.huggler.balloonmail

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EntityScan(basePackageClasses = [BalloonMailApplication::class])
@SpringBootApplication(scanBasePackages = ["io.huggler.balloonmail"])
class BalloonMailApplication

fun main(args: Array<String>) {
	runApplication<BalloonMailApplication>(*args)
}
