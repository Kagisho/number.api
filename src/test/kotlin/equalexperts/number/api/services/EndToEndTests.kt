package equalexperts.number.api.services

import equalexperts.number.api.models.DayOfTheWeekEnum
import equalexperts.number.api.models.NumberMetadataResponse
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication
import jdk.jfr.Description
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class EndToEndTests {
    @Test
    @DisplayName("default endpoint - check welcome text")
    fun `default endpoint - test welcome text`() = testApplication {
        val response = client.get("/")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(
            "Welcome to the Numbers Api. Call /swagger to view Open API Documentation. " +
                "Call /number/<your_number> to get a metadata result.",
            response.bodyAsText()
        )
    }

    @ParameterizedTest
    @MethodSource("equalexperts.number.api.services.TestHelpers#provideNumbersForDaysOfTheWeek")
    @DisplayName("getMetaData endpoint - valid request parameter value")
    fun `getMetaData endpoint - valid day of the week`(number: String, expected: DayOfTheWeekEnum) = testApplication {
        val response = client.get("/number/$number") {
            accept(ContentType.Application.Json)
        }

        if (response.status == HttpStatusCode.OK) {
            val numberMetaDataResponse = Json.decodeFromString<NumberMetadataResponse>(response.bodyAsText())
            assertEquals(expected, numberMetaDataResponse.nameOfDayOfTheWeek)
        }
    }

    @Test
    @DisplayName("getMetaData endpoint - check excluded X-Request-Id header")
    @Description(
        "The X-Request-Id is automatically generated if it is not provided. This test ensures that it is " +
            "generated when it is not provided."
    )

    fun `getMetaData endpoint - check excluded X-Request-Id header`() = testApplication {
        val response = client.get("/number/1") {
            accept(ContentType.Application.Json)
        }
        assertTrue(response.headers[HttpHeaders.XRequestId] != null)
    }

    @Test
    @DisplayName("getMetaData endpoint - check included X-Request-Id header")
    @Description(
        "The X-Request-Id is returned when it is populated in the request. This test ensures that the " +
            "header passed in is returned in the response"
    )
    fun `getMetaData endpoint - check included X-Request-Id header`() = testApplication {
        val requestedId = "requested-id"
        val response = client.get("/number/1") {
            accept(ContentType.Application.Json)
            headers {
                append(HttpHeaders.XRequestId, requestedId)
            }
        }
        assertEquals(requestedId, response.headers[HttpHeaders.XRequestId])
    }

    @Test
    @DisplayName("getMetaData endpoint - bad request test")
    @Description("Invalid input must return error 400")
    fun `getMetaData endpoint - bad request test`() = testApplication {
        val response = client.get("/number/invalid") {
            accept(ContentType.Application.Json)
        }
        assertEquals(HttpStatusCode.BadRequest, response.status)
    }
}
