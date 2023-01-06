package equalexperts.number.api.functions

import equalexperts.number.api.models.DayOfTheWeekEnum
import equalexperts.number.api.models.NumberMetadataResponse

object ExtensionFunctions {
    fun String.isNumber(): Boolean {
        if (this.isNullOrEmpty()) return false

        return this.all { Character.isDigit(it) }
    }

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
