package equalexperts.number.api.models

import kotlinx.serialization.Serializable

@Serializable
data class NumberMetadataResponse(var number: Long = 0) {
    var nameOfDayOfTheWeek: DayOfTheWeekEnum = DayOfTheWeekEnum.Unknown

    var isEven: Boolean? = null
}
