package equalexperts.number.api.plugins

import equalexperts.number.api.functions.ExtensionFunctions.isNumber
import equalexperts.number.api.models.NumberMetadataResponse
import equalexperts.number.api.services.NumberService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.metrics.micrometer.MicrometerMetrics
import io.ktor.server.plugins.swagger.swaggerUI
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import io.micrometer.core.instrument.binder.jvm.JvmGcMetrics
import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics
import io.micrometer.core.instrument.binder.system.ProcessorMetrics
import io.micrometer.prometheus.PrometheusConfig
import io.micrometer.prometheus.PrometheusMeterRegistry
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val numberService by inject<NumberService>()

    val appMicrometerRegistry = PrometheusMeterRegistry(PrometheusConfig.DEFAULT)
    install(MicrometerMetrics) {
        registry = appMicrometerRegistry
        timers { call, _ ->
            call.request.headers["regionId"]?.let { tag("region", it) }
        }

        meterBinders = listOf(
            JvmMemoryMetrics(),
            JvmGcMetrics(),
            ProcessorMetrics()
        )
    }

    routing {
        swaggerUI(path = "swagger", swaggerFile = "openapi/documentation.yaml")

        route("/") {
            get {
                call.respondText(
                    "Welcome to the Numbers Api. Call /swagger to view Open API Documentation. Call " +
                        "/number/<your_number> to get a metadata result.",
                    status = HttpStatusCode.OK
                )
            }

            get("/metrics") {
                call.respond(appMicrometerRegistry.scrape())
            }

            get("number/{number}") {

                val number = call.parameters["number"]
                if (number?.isNumber() == true) {
                    val num = number.toLong()
                    val numberResponse: NumberMetadataResponse = numberService.getNumberMetadata(num)
                    call.respond(HttpStatusCode.OK, numberResponse)
                } else {
                    call.respondText(
                        "Input '$number' is an invalid number. Please input an integer.",
                        status = HttpStatusCode.BadRequest
                    )
                }
            }
        }
    }
}
