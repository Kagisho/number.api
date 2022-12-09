package com.example.services

import com.example.functions.StaticFunctions
import com.example.models.NumberMetadataResponse

class NumberService : INumberService {
    override fun getNumberMetadata(number: Int): NumberMetadataResponse {

        val num = NumberMetadataResponse().apply {
            this.number = number
            this.nameOfDayOfTheWeek =  StaticFunctions.getDayOfTheWeekName(number)
        }
        return num
    }

}