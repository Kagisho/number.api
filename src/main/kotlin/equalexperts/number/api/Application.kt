package equalexperts.number.api

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import equalexperts.number.api.plugins.configureRouting
import equalexperts.number.api.plugins.configureSerialization
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.callid.CallId
import io.ktor.server.plugins.callid.generate
import io.ktor.server.plugins.callloging.CallLogging
import io.ktor.server.plugins.compression.Compression
import io.ktor.server.plugins.compression.deflate
import io.ktor.server.plugins.compression.minimumSize
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.doublereceive.DoubleReceive
import io.ktor.server.request.httpMethod
import io.ktor.server.request.path
import io.ktor.server.request.uri
import io.ktor.server.resources.Resources
import kotlinx.serialization.json.Json
import org.koin.ktor.plugin.Koin
import org.slf4j.event.Level

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1", module = Application::module)
        .start(wait = true)
}

const val MINIMUM_PAYLOAD_SIZE: Long = 3
const val LENGTH_OF_REQUEST_ID: Int = 15
const val REQUEST_ID_GENERATOR_DICTIONARY: String = "abcdeghijklmnop1234567890"

val mapper = jacksonObjectMapper()

@Suppress("unused")
fun Application.module() {
    install(Koin) {
        modules(appModule)
    }

    install(DoubleReceive)
    install(Resources)
    install(CallId) {
        generate(LENGTH_OF_REQUEST_ID, REQUEST_ID_GENERATOR_DICTIONARY)
        retrieveFromHeader(HttpHeaders.XRequestId)
        replyToHeader(HttpHeaders.XRequestId)
    }

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
                val XRequestId = call.request.headers[HttpHeaders.XRequestId]
            }
            mapper.writeValueAsString(logObject)
        }
    }

    install(Compression) {
        deflate {
            minimumSize(MINIMUM_PAYLOAD_SIZE)
        }
    }

    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
            }
        )
    }

    configureSerialization()
    configureRouting()
}
