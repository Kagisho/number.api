package equalexperts.number.api.services

import equalexperts.number.api.models.DayOfTheWeekEnum
import org.junit.jupiter.params.provider.Arguments
import java.util.stream.Stream

object TestHelpers {
        @JvmStatic
        fun provideNumbersForDaysOfTheWeek(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of("1", DayOfTheWeekEnum.Sunday),
                Arguments.of("2", DayOfTheWeekEnum.Monday),
                Arguments.of("3", DayOfTheWeekEnum.Tuesday),
                Arguments.of("4", DayOfTheWeekEnum.Wednesday),
                Arguments.of("5", DayOfTheWeekEnum.Thursday),
                Arguments.of("6", DayOfTheWeekEnum.Friday),
                Arguments.of("7", DayOfTheWeekEnum.Saturday),
                Arguments.of("0", DayOfTheWeekEnum.Unknown),
                Arguments.of("-1", DayOfTheWeekEnum.Unknown),
                Arguments.of("8", DayOfTheWeekEnum.Unknown),
                Arguments.of("99999999999", DayOfTheWeekEnum.Unknown),
                Arguments.of("invalid", DayOfTheWeekEnum.Unknown),
            )
    }
}
