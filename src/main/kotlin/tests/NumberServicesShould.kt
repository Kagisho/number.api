package tests

import equalexperts.number.api.models.DayOfTheWeekEnum
import equalexperts.number.api.services.NumberService
import org.testng.Assert.assertEquals
import org.testng.Assert.assertNotEquals
import org.testng.annotations.Test

internal class NumberServicesShould {
    // TODO Need to figure out how parameterised tests works for JUNit
    // TODO @DisplayName is not working and the appropriate library for it doesn't get loaded\imported.
    @Test
    fun getNumberMetadataReturnDayOfWeekBasedOnInput() {
        val numberService = NumberService()

        for (idx in LOOPSTART..LOOPEND) {

            var dayOfTheWeekName = numberService.getNumberMetadata(idx)

            if (idx in DayOfTheWeekEnum.Sunday.numericValue .. DayOfTheWeekEnum.Saturday.numericValue) {
                assertNotEquals(dayOfTheWeekName.nameOfDayOfTheWeek, DayOfTheWeekEnum.Unknown)
            }
            else {
                assertEquals(dayOfTheWeekName.nameOfDayOfTheWeek, DayOfTheWeekEnum.Unknown)
            }
        }
    }

    companion object {
        const val LOOPSTART = -10
        const val LOOPEND = 10
    }
}
