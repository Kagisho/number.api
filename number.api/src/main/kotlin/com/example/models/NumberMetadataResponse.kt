package com.example.models

import kotlinx.serialization.Serializable

@Serializable
class NumberMetadataResponse {
    var number : Int = 0
    var nameOfDayOfTheWeek = DayOfTheWeekEnum.Unknown
}