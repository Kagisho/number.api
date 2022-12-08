package tests

import com.example.functions.Statics
import com.example.models.DayOfTheWeekEnum
import org.testng.Assert.assertEquals
import org.testng.Assert.assertNotEquals
import org.testng.annotations.Test

internal class FunctionsShould {

    // TODO: Need to figure out how parametarised tests works for JUNit
    // TODO: @DisplayName is not working and the appropriate library for it doesn't get loaded\imported.
    @Test
    fun getDayOfTheWeekName_return_day_of_week_based_on_input() {
        for (idx in -1..10) {
            var dayOfTheWeekName = Statics.getDayOfTheWeekName(idx)

            if (idx in 1..7) {
                assertNotEquals(dayOfTheWeekName, DayOfTheWeekEnum.Unknown)
            }
            else {
                assertEquals(dayOfTheWeekName, DayOfTheWeekEnum.Unknown)
            }
        }
    }

}