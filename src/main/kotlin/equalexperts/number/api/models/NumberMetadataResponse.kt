package equalexperts.number.api.models

import kotlinx.serialization.Serializable

@Serializable
data class NumberMetadataResponse(var number : Int = 0){
    // @SerialName("nameOfDayOfTheWeek")
    var nameOfDayOfTheWeek : DayOfTheWeekEnum  = DayOfTheWeekEnum.Unknown

  //  @SerialName("isEven")
    var isEven : Boolean? = null
}
