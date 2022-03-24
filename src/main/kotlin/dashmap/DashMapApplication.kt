package dashmap

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DashMapApplication

fun main(args: Array<String>) {
	runApplication<DashMapApplication>(*args)
}
