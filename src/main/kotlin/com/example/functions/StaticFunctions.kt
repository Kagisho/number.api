package com.example.functions

import com.example.models.DayOfTheWeekEnum
import com.example.models.WeekDayIndex.FRIDAY
import com.example.models.WeekDayIndex.MONDAY
import com.example.models.WeekDayIndex.SATURDAY
import com.example.models.WeekDayIndex.SUNDAY
import com.example.models.WeekDayIndex.THURSDAY
import com.example.models.WeekDayIndex.TUESDAY
import com.example.models.WeekDayIndex.WEDNESDAY

object StaticFunctions {
        fun getDayOfTheWeekName(number: Int): DayOfTheWeekEnum {
            return when (number) {
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

        fun String.isNumber(): Boolean {

            if (this == null) return false

            return this.all { Character.isDigit(it) }
        }
 }

