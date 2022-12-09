package com.example.functions

import com.example.models.DayOfTheWeekEnum

class Statics {
    companion object {
        fun getDayOfTheWeekName(number: Int): DayOfTheWeekEnum {
            return when (number) {
                1 -> DayOfTheWeekEnum.Sunday
                2 -> DayOfTheWeekEnum.Monday
                3 -> DayOfTheWeekEnum.Tuesday
                4 -> DayOfTheWeekEnum.Wednesday
                5 -> DayOfTheWeekEnum.Thursday
                6 -> DayOfTheWeekEnum.Friday
                7 -> DayOfTheWeekEnum.Saturday
                else -> DayOfTheWeekEnum.Unknown
            }
        }

        fun String.isNumber() : Boolean {

            if (this == null) return false

            return this.all { Character.isDigit(it)}
        }
    }
}