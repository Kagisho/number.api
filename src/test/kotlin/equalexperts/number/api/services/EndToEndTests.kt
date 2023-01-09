package equalexperts.number.api.services

import equalexperts.number.api.models.DayOfTheWeekEnum
import equalexperts.number.api.models.NumberMetadataResponse
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class EndToEndTests {
    @Test
    @DisplayName("default endpoint - check welcome text")
    fun `default endpoint - test welcome text`() = testApplication {
        val response = client.get("/")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Welcome to the Numbers Api. Call /swagger to view Open API Documentation. Call /number/<your_number> to get a metadata result.", response.bodyAsText())
    }

    @Test
    @DisplayName("getMetaData endpoint - valid day of the week")
    fun `getMetaData endpoint - valid day of the week`() = testApplication {
        val response = client.get("/number/2"){
            accept(ContentType.Application.Json)
        }

        val numberMetaDataResponse  =  Json.decodeFromString<NumberMetadataResponse>(response.bodyAsText())

        assertEquals(DayOfTheWeekEnum.Monday, numberMetaDataResponse.nameOfDayOfTheWeek)
    }

    @Test
    @DisplayName("getMetaData endpoint - invalid day of the week")
    fun `getMetaData endpoint - invalid day of the week`() = testApplication {
        val response = client.get("/number/10"){
            accept(ContentType.Application.Json)
        }

        val numberMetaDataResponse  =  Json.decodeFromString<NumberMetadataResponse>(response.bodyAsText())

        assertEquals(DayOfTheWeekEnum.Unknown, numberMetaDataResponse.nameOfDayOfTheWeek)
    }


}