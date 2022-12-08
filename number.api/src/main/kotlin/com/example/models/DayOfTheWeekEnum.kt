package com.example.models

import kotlinx.serialization.Serializable

@Serializable
enum class DayOfTheWeekEnum {
    Sunday,
    Monday,
    Tuesday,
    Wednesday,
    Thursday,
    Friday,
    Saturday,
    Unknown
}