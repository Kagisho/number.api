package equalexperts.number.api.services

import equalexperts.number.api.models.DayOfTheWeekEnum
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName

internal class NumberServiceTest {
    // TODO Need to figure out how parameterised tests works for JUNit
    @Test
    @DisplayName("Return the correct day of the week given an integer input")
   fun getNumberMetadataTest() {
 //   fun `getNumberMetadataReturnDayOfWeekBasedOnInput`() {
        val numberService = NumberService()

        for (idx in LOOPSTART..LOOPEND) {

            var dayOfTheWeekName = numberService.getNumberMetadata(idx)

            if (idx in DayOfTheWeekEnum.Sunday.numericValue .. DayOfTheWeekEnum.Saturday.numericValue) {
                //assertNotEquals(dayOfTheWeekName.nameOfDayOfTheWeek, DayOfTheWeekEnum.Unknown)
                Assertions.assertNotEquals(dayOfTheWeekName.nameOfDayOfTheWeek, DayOfTheWeekEnum.Unknown)
            }
            else {
                Assertions.assertEquals(dayOfTheWeekName.nameOfDayOfTheWeek, DayOfTheWeekEnum.Unknown)
            }
        }
    }

    companion object {
        const val LOOPSTART = -10
        const val LOOPEND = 10
    }
}
