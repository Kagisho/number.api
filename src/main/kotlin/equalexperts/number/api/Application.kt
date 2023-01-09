package equalexperts.number.api

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import equalexperts.number.api.plugins.configureRouting
import equalexperts.number.api.plugins.configureSerialization
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.metrics.micrometer.MicrometerMetrics
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
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import io.micrometer.core.instrument.binder.jvm.JvmGcMetrics
import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics
import io.micrometer.core.instrument.binder.system.ProcessorMetrics
import io.micrometer.prometheus.PrometheusConfig
import io.micrometer.prometheus.PrometheusMeterRegistry
import kotlinx.serialization.json.Json
import org.slf4j.event.Level

fun main(args: Array<String>): Unit {

    //io.ktor.server.netty.EngineMain.main(args)

//    val env = applicationEngineEnvironment {
//        module {
//            io.ktor.server.netty.EngineMain.main(args)
//        }
//    }
//    embeddedServer(Netty, env).start(true)

    embeddedServer(Netty, port = 8080, host = "127.0.0.1", module = Application::module)
        .start(wait = true)
}

const val  MINIMUMPAYLOADSIZE : Long = 3
const val  LENGTHOFREQUESTID : Int = 15
const val  REQUESTIDGENERATORDICTIONARY : String = "abcdeghijklmnop1234567890"

val mapper = jacksonObjectMapper()

@Suppress("unused","MagicNumber")// application.conf references the main function. This annotation prevents the IDE from
// marking it as unused.
fun Application.module() {
    install(DoubleReceive)

    install(CallId) {
        generate(LENGTHOFREQUESTID, REQUESTIDGENERATORDICTIONARY)
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
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }

    configureSerialization()

    configureRouting()

    val appMicrometerRegistry = PrometheusMeterRegistry(PrometheusConfig.DEFAULT)
    install(MicrometerMetrics)
    {
        registry = appMicrometerRegistry
        timers { call, exception ->
            tag("region", call.request.headers["regionId"])
        }

        meterBinders = listOf(
            JvmMemoryMetrics(),
            JvmGcMetrics(),
            ProcessorMetrics()
        )
    }

    routing {
        get("/metrics") {
            call.respond(appMicrometerRegistry.scrape())
        }
    }
}

