package equalexperts.number.api.functions

import equalexperts.number.api.models.DayOfTheWeekEnum
import equalexperts.number.api.models.WeekDayIndex.FRIDAY
import equalexperts.number.api.models.WeekDayIndex.MONDAY
import equalexperts.number.api.models.WeekDayIndex.SATURDAY
import equalexperts.number.api.models.WeekDayIndex.SUNDAY
import equalexperts.number.api.models.WeekDayIndex.THURSDAY
import equalexperts.number.api.models.WeekDayIndex.TUESDAY
import equalexperts.number.api.models.WeekDayIndex.WEDNESDAY

object ExtensionFunctions {
    fun Int.toDayOfTheWeek(): DayOfTheWeekEnum {
        return when (this) {
            SUNDAY -> DayOfTheWeekEnum.Sunday
            MONDAY -> DayOfTheWeekEnum.Monday
            TUESDAY -> DayOfTheWeekEnum.Tuesday
            WEDNESDAY -> DayOfTheWeekEnum.Wednesday
            THURSDAY -> DayOfTheWeekEnum.Thursday
            FRIDAY -> DayOfTheWeekEnum.Friday
            SATURDAY -> DayOfTheWeekEnum.Saturday
            else -> DayOfTheWeekEnum.Unknown
        }
    }
}
