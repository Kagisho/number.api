package equalexperts.number.api

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import equalexperts.number.api.plugins.configureRouting
import equalexperts.number.api.plugins.configureSerialization
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.callloging.CallLogging
import io.ktor.server.plugins.compression.Compression
import io.ktor.server.plugins.compression.deflate
import io.ktor.server.plugins.compression.minimumSize
import io.ktor.server.request.httpMethod
import io.ktor.server.request.path
import io.ktor.server.request.uri
import org.slf4j.event.Level

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

const val  MINIMUMPAYLOADSIZE : Long = 3
val mapper = jacksonObjectMapper()

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from
// marking it as unused.
fun Application.module() {
    install(CallLogging) {
        level = Level.INFO

        filter { call ->
            call.request.path().startsWith("/number")
        }

        format { call ->
                        val logObject = object {
                            val Endpoint = call.request.uri
                            val HttpStatusCode = call.response.status()?.value
                            val HttpMethod = call.request.httpMethod.value
                            val UserAgent = call.request.headers["User-Agent"]
                        }
            mapper.writeValueAsString(logObject)
        }
    }

    install(Compression) {
           deflate {
            minimumSize(MINIMUMPAYLOADSIZE)
        }
    }

    configureSerialization()
    configureRouting()
}

