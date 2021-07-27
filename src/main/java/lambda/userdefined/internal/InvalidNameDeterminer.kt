package lambda.userdefined.internal

import lambda.core.constants.ReservedValuesConstants

class InvalidNameDeterminer(
    private val invalidNames: Set<String>
) {
    fun isInvalidName(
        value: String
    ): Boolean {
        return invalidNames.contains(value) || value.matches(Regex(ReservedValuesConstants.NUMERIC_PATTERN))
    }
}