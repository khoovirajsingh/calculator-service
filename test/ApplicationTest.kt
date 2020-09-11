package com.katas

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.features.*
import kotlin.test.*
import io.ktor.server.testing.*

class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("HELLO WORLD!", response.content)
            }
        }
    }

    @Test
    fun addition() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/add?number1=1&number2=1").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("2", response.content)
            }

            handleRequest(HttpMethod.Get, "/add?number1=1&number2=2").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("3", response.content)
            }
        }
    }
}
