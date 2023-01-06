package equalexperts.number.api.functions

import equalexperts.number.api.models.DayOfTheWeekEnum
import equalexperts.number.api.models.NumberMetadataResponse

object ExtensionFunctions {
    fun String.isNumber(): Boolean {
        if (this.isNullOrEmpty()) return false

        return this.all { Character.isDigit(it) }
    }

//    fun Int.toDayOfTheWeek(): DayOfTheWeekEnum {
//        return when (this) {
//            SUNDAY -> DayOfTheWeekEnum.Sunday
//            MONDAY -> DayOfTheWeekEnum.Monday
//            TUESDAY -> DayOfTheWeekEnum.Tuesday
//            WEDNESDAY -> DayOfTheWeekEnum.Wednesday
//            THURSDAY -> DayOfTheWeekEnum.Thursday
//            FRIDAY -> DayOfTheWeekEnum.Friday
//            SATURDAY -> DayOfTheWeekEnum.Saturday
//            else -> DayOfTheWeekEnum.Unknown
//        }
//    }

    fun NumberMetadataResponse.addDayOfTheWeek(number : Int) : NumberMetadataResponse
    {
         this.number = number
         this.nameOfDayOfTheWeek = DayOfTheWeekEnum.values().firstOrNull { it.numericValue == number}
                                    ?: DayOfTheWeekEnum.Unknown
         return this
    }

    fun NumberMetadataResponse.addIsEven(number : Int) : NumberMetadataResponse
    {
        this.isEven = number % 2 == 0
        return this
    }


}
