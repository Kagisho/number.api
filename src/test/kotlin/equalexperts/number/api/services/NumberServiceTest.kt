package equalexperts.number.api.services

import equalexperts.number.api.models.DayOfTheWeekEnum
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("Number Service Tests")
@TestInstance(PER_CLASS)
class NumberServiceTest {

    fun provideNumbersForDaysOfTheWeek(): Stream<Arguments?>? {
        return Stream.of(
            Arguments.of(1, DayOfTheWeekEnum.Sunday),
            Arguments.of(2, DayOfTheWeekEnum.Monday),
            Arguments.of(3, DayOfTheWeekEnum.Tuesday),
            Arguments.of(4, DayOfTheWeekEnum.Wednesday),
            Arguments.of(5, DayOfTheWeekEnum.Thursday),
            Arguments.of(6, DayOfTheWeekEnum.Friday),
            Arguments.of(7, DayOfTheWeekEnum.Saturday),
            Arguments.of(0, DayOfTheWeekEnum.Unknown),
            Arguments.of(-1, DayOfTheWeekEnum.Unknown),
            Arguments.of(8, DayOfTheWeekEnum.Unknown),
        )
    }

   @ParameterizedTest
   @MethodSource("provideNumbersForDaysOfTheWeek")
   @DisplayName("provide numbers for days of the week return correct day of the week enum")
    fun `getNumberMetadata tests`(number : Int,  expected : DayOfTheWeekEnum) {
        val numberService = NumberService()
        var dayOfTheWeekName = numberService.getNumberMetadata(number)
        Assertions.assertEquals(dayOfTheWeekName.nameOfDayOfTheWeek, expected)
    }
}
