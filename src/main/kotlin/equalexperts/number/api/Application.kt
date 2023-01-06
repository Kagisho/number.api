package equalexperts.number.api

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import equalexperts.number.api.functions.ExtensionFunctions.isNumber
import equalexperts.number.api.plugins.configureRouting
import equalexperts.number.api.plugins.configureSerialization
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.applicationEngineEnvironment
import io.ktor.server.engine.connector
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
import io.ktor.server.plugins.requestvalidation.RequestValidation
import io.ktor.server.plugins.requestvalidation.RequestValidationException
import io.ktor.server.plugins.requestvalidation.ValidationResult
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.request.httpMethod
import io.ktor.server.request.path
import io.ktor.server.request.uri
import io.ktor.server.response.respond
import org.slf4j.event.Level

fun main(args: Array<String>): Unit {
    //io.ktor.server.netty.EngineMain.main(args)
    val env = applicationEngineEnvironment {
        module {
            io.ktor.server.netty.EngineMain.main(args)
        }
        // Private API

        connector {
            host = "127.0.0.1"
            port = 9090
        }
        // Public API
        connector {
            host = "0.0.0.0"
            port = 8080
        }
    }

    embeddedServer(Netty, env).start(true)
}

const val  MINIMUMPAYLOADSIZE : Long = 3
val mapper = jacksonObjectMapper()

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from
// marking it as unused.
fun Application.module() {
    install(DoubleReceive)

    install(CallId) {
        generate(15, "abcdeghijklmnop1234567890")
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
            minimumSize(MINIMUMPAYLOADSIZE)
        }
    }

    install(ContentNegotiation) {
        json()
    }

    configureSerialization()
    configureRouting()

    install(RequestValidation) {

        validate<String> { bodyText ->

            if (!bodyText?.isNumber()!!)
                ValidationResult.Invalid("Input '$bodyText' is an invalid number. Please input an integer...")
            else
                ValidationResult.Valid
        }

        install(StatusPages) {
            exception<RequestValidationException> { call, cause ->
                call.respond(HttpStatusCode.BadRequest, cause.reasons.joinToString())
            }
        }
    }
}

