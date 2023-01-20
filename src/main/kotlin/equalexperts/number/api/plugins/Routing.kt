package equalexperts.number.api.plugins

import equalexperts.number.api.functions.ExtensionFunctions.isNumber
import equalexperts.number.api.models.NumberMetadataResponse
import equalexperts.number.api.services.NumberService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.plugins.swagger.swaggerUI
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing


fun Application.configureRouting() {

    // TODO Is there a way to load this using dependency injection?
      routing {
        swaggerUI(path = "swagger", swaggerFile = "documentation.yaml")

        route("/") {
            get {
                call.respondText(
                    "Welcome to the Numbers Api. Call /swagger to view Open API Documentation. Call " +
                            "/number/<your_number> to get a metadata result.",
                    status = HttpStatusCode.OK
                )
            }
            get("number/{number}") {

               val number = call.parameters["number"]

                if (number?.isNumber() == true) {
                    val num =  number.toLong()
                    val numberService = NumberService();
                    var numberResponse : NumberMetadataResponse = numberService.getNumberMetadata(num)
                    call.respond(HttpStatusCode.OK, numberResponse)
                }
                else {
                    call.respondText(
                        "Input '$number' is an invalid number. Please input an integer.",
                        status = HttpStatusCode.BadRequest
                    )
                }
            }

        }
    }
}

