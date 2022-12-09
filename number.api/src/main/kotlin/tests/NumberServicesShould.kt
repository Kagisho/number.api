package tests

import com.example.models.DayOfTheWeekEnum
import com.example.services.NumberService
import org.testng.Assert.assertEquals
import org.testng.Assert.assertNotEquals
import org.testng.annotations.Test

internal class NumberServicesShould {

    // TODO: Need to figure out how parametarised tests works for JUNit
    // TODO: @DisplayName is not working and the appropriate library for it doesn't get loaded\imported.
    @Test
    fun getNumberMetadata_return_day_of_week_based_on_input() {
        val numberService = NumberService()
        for (idx in -10..10) {

            var dayOfTheWeekName = numberService.getNumberMetadata(idx)

            if (idx in 1..7) {
                assertNotEquals(dayOfTheWeekName.nameOfDayOfTheWeek, DayOfTheWeekEnum.Unknown)
            }
            else {
                assertEquals(dayOfTheWeekName.nameOfDayOfTheWeek, DayOfTheWeekEnum.Unknown)
            }
        }
    }

}