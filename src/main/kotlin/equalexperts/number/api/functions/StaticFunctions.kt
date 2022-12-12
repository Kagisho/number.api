package equalexperts.number.api.functions

object StaticFunctions {

       fun String.isNumber(): Boolean {
            if (this.isNullOrEmpty()) return false

            return this.all { Character.isDigit(it) }
        }
 }

