package equalexperts.number.api.services

import equalexperts.number.api.functions.ExtensionFunctions.toDayOfTheWeek
import equalexperts.number.api.models.NumberMetadataResponse

class NumberService : NumberServiceInterface {
    override fun getNumberMetadata(number: Int): NumberMetadataResponse {

        return NumberMetadataResponse(number, number.toDayOfTheWeek())
    }
}
