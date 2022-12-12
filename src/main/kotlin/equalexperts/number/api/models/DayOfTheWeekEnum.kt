package equalexperts.number.api.models

import equalexperts.number.api.models.WeekDayIndex.FRIDAY
import equalexperts.number.api.models.WeekDayIndex.MONDAY
import equalexperts.number.api.models.WeekDayIndex.SATURDAY
import equalexperts.number.api.models.WeekDayIndex.SUNDAY
import equalexperts.number.api.models.WeekDayIndex.THURSDAY
import equalexperts.number.api.models.WeekDayIndex.TUESDAY
import equalexperts.number.api.models.WeekDayIndex.UNKNOWN
import equalexperts.number.api.models.WeekDayIndex.WEDNESDAY
import kotlinx.serialization.Serializable

@Serializable
enum class DayOfTheWeekEnum(val numericValue: Int, val displayName: String) {
    Sunday(SUNDAY, "Sunday"),
    Monday(MONDAY, "Monday"),
    Tuesday(TUESDAY, "Tuesday"),
    Wednesday(WEDNESDAY, "Wednesday"),
    Thursday(THURSDAY, "Thursday"),
    Friday(FRIDAY, "Friday"),
    Saturday(SATURDAY, "Saturday"),
    Unknown(UNKNOWN, "Unknown")
}
