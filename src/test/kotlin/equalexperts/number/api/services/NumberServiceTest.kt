package equalexperts.number.api.services

import equalexperts.number.api.models.DayOfTheWeekEnum
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Number Service Tests")
@TestInstance(PER_CLASS)
class NumberServiceTest {
   @ParameterizedTest
   @MethodSource("equalexperts.number.api.services.TestHelpers#provideNumbersForDaysOfTheWeek")
   @DisplayName("provide numbers for days of the week return correct day of the week enum")
    fun `getNumberMetadata tests`(number : Int,  expected : DayOfTheWeekEnum) {
        val numberService = NumberService()
        var dayOfTheWeekName = numberService.getNumberMetadata(number)
        Assertions.assertEquals(dayOfTheWeekName.nameOfDayOfTheWeek, expected)
    }
}
