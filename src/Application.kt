package com.katas

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.features.*
import java.lang.NumberFormatException

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        get("/add") {
            val number1 = call.parameters["number1"]?.toInt() ?: 0
            val number2 = call.parameters["number2"]?.toInt() ?: 0
            val sum = number1 + number2
            call.respondText("$sum", contentType = ContentType.Text.Plain)

        }

        install(StatusPages) {
            exception<NumberFormatException> {
                call.respond(HttpStatusCode.BadRequest)
            }
            exception<AuthenticationException> { cause ->
                call.respond(HttpStatusCode.Unauthorized)
            }
            exception<AuthorizationException> { cause ->
                call.respond(HttpStatusCode.Forbidden)
            }

        }
    }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()

