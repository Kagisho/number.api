package com.example.services

import com.example.functions.Statics
import com.example.models.NumberMetadataResponse

class NumberService : INumberService {
    override fun getNumberMetadata(number: Int): NumberMetadataResponse {
        var num = NumberMetadataResponse()
        num.nameOfDayOfTheWeek =  Statics.getDayOfTheWeekName(number)
        num.number = number

        return num
    }

}