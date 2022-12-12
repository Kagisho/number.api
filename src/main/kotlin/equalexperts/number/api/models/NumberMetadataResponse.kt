package equalexperts.number.api.models

import kotlinx.serialization.Serializable

@Serializable
data class NumberMetadataResponse (val number : Int, val nameOfDayOfTheWeek : DayOfTheWeekEnum)
