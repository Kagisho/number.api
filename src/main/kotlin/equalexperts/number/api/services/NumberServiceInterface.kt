package equalexperts.number.api.services

import equalexperts.number.api.models.NumberMetadataResponse

interface NumberServiceInterface {
    fun getNumberMetadata(number : Int) : NumberMetadataResponse
}
