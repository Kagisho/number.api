package com.example.models

import com.example.models.WeekDayIndex.FRIDAY
import com.example.models.WeekDayIndex.MONDAY
import com.example.models.WeekDayIndex.SATURDAY
import com.example.models.WeekDayIndex.SUNDAY
import com.example.models.WeekDayIndex.THURSDAY
import com.example.models.WeekDayIndex.TUESDAY
import com.example.models.WeekDayIndex.UNKNOWN
import com.example.models.WeekDayIndex.WEDNESDAY
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
