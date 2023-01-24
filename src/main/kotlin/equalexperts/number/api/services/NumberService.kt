package equalexperts.number.api.services

import equalexperts.number.api.functions.ExtensionFunctions.addDayOfTheWeek
import equalexperts.number.api.functions.ExtensionFunctions.addIsEven
import equalexperts.number.api.models.NumberMetadataResponse

class NumberService : NumberServiceInterface {
    override fun getNumberMetadata(number: Long): NumberMetadataResponse {
        return NumberMetadataResponse()
            .addDayOfTheWeek(number)
            .addIsEven(number)
    }
}
