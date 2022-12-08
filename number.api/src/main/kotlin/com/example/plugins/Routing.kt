package com.example.plugins

import com.example.services.NumberService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    val _numberService = NumberService()

    routing {
        route("/number") {
            get {
                call.respondText("Welcome to the number api. Call /number/<your_number> to get a result", status = HttpStatusCode.OK)
            }

            get("{number}") {
                val number = call.parameters["number"]
                var isNumber = number?.all { Character.isDigit(it)}

                if(isNumber == true) {
                    val numberResponse = _numberService.getNumberMetadata(number!!.toInt())
                    call.respond(numberResponse)
                }
                else {
                    call.respondText ("Input number $number is invalid. Please input an integer" )
                }
            }
        }
    }
 }
