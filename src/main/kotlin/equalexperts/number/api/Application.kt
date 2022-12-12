package equalexperts.number.api

import equalexperts.number.api.plugins.configureRouting
import equalexperts.number.api.plugins.configureSerialization
import io.ktor.server.application.Application

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from
// marking it as unused.
fun Application.module() {
    configureSerialization()
    configureRouting()
}

