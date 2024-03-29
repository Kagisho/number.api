package equalexperts.number.api.functions

import equalexperts.number.api.models.DayOfTheWeekEnum
import equalexperts.number.api.models.NumberMetadataResponse

object ExtensionFunctions {
    fun String.isNumber(): Boolean {
        if (this.isEmpty()) return false

        return try {
            this.toBigInteger()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }

    fun NumberMetadataResponse.addDayOfTheWeek(number: Long): NumberMetadataResponse {
        this.number = number
        this.nameOfDayOfTheWeek = DayOfTheWeekEnum.values().firstOrNull { it.numericValue == number }
            ?: DayOfTheWeekEnum.Unknown
        return this
    }

    fun NumberMetadataResponse.addIsEven(number: Long): NumberMetadataResponse {
        this.isEven = number % 2 == 0L
        return this
    }
}
