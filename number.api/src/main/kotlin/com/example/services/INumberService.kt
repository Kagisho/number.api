package com.example.services

import com.example.models.NumberMetadataResponse

interface INumberService {
    fun getNumberMetadata(number : Int) : NumberMetadataResponse
}