package com.example.plugins

import com.example.functions.Statics.Companion.isNumber
import com.example.services.NumberService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    // TODO: Wire up logging
    // TODO: Wire up swagger
    // TODO: Is there a way to load this using dependency injection?
    val numberService = NumberService()

    routing {

        route("/") {
            get {
                call.respondText ("Welcome to the Number Api. Call /number/<your_number> to get a result", status = HttpStatusCode.OK)
            }

            get("number/{number}") {
                val number = call.parameters["number"]

                if(number?.isNumber() == true) {
                    val numberResponse = numberService.getNumberMetadata(number!!.toInt())
                    call.respond(numberResponse)
                }
                else {
                    call.respondText ("Input $number is an invalid number. Please input an integer" )
                }
            }
        }
    }
 }
