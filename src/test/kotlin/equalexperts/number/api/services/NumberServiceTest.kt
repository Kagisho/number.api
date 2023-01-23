package equalexperts.number.api.services

import equalexperts.number.api.functions.ExtensionFunctions.isNumber
import equalexperts.number.api.models.DayOfTheWeekEnum
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Number Service Tests")
@TestInstance(PER_CLASS)
class NumberServiceTest {

    private var numberService: NumberServiceInterface? = null

    @BeforeEach
    fun initialise() {
        numberService = NumberService()
    }

    @ParameterizedTest
    @MethodSource("equalexperts.number.api.services.TestHelpers#provideNumbersForDaysOfTheWeek")
    @DisplayName("provide numbers for days of the week return correct day of the week enum")
    fun `getNumberMetadata tests`(number: String, expected: DayOfTheWeekEnum) {
        if (number.isNumber()) {
            val dayOfTheWeekName = numberService?.getNumberMetadata(number.toLong())
            Assertions.assertEquals(dayOfTheWeekName?.nameOfDayOfTheWeek, expected)
        }
    }
}
