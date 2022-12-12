package com.example.plugins

import com.example.functions.StaticFunctions.isNumber
import com.example.services.NumberService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun Application.configureRouting() {

    // TODO Wire up logging
    // TODO Wire up swagger
    // TODO Is there a way to load this using dependency injection?
    val numberService = NumberService()

    routing {
        // TODO May need to put these routes in their own package\module\class
        route("/") {
            get {
                call.respondText(
                    "Welcome to the Numbers Api. Call /number/<your_number> to get a metadata result.",
                    status = HttpStatusCode.OK
                )
            }

            get("number/{number}") {
                val number = call.parameters["number"]

                if (number?.isNumber() == true) {
                    val numberResponse = numberService.getNumberMetadata(number!!.toInt())
                    call.respond(numberResponse)
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

