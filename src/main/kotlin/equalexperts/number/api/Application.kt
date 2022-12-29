package equalexperts.number.api

import equalexperts.number.api.plugins.configureRouting
import equalexperts.number.api.plugins.configureSerialization
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.compression.Compression
import io.ktor.server.plugins.compression.deflate
import io.ktor.server.plugins.compression.minimumSize


fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

const val  MINIMUMPAYLOADSIZE : Long = 3

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from
// marking it as unused.
fun Application.module() {

    install(Compression) {
           deflate {
            minimumSize(MINIMUMPAYLOADSIZE)
        }
    }
    configureSerialization()
    configureRouting()
}

