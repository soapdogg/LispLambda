package lambda.userdefined

import lambda.core.constants.ReservedValuesConstants

internal interface InvalidNameDeterminer {
    fun isInvalidName(
        value: String
    ): Boolean
}

internal class InvalidNameDeterminerImpl(
    private val invalidNames: Set<String>
): InvalidNameDeterminer {
    override fun isInvalidName(
        value: String
    ): Boolean {
        return invalidNames.contains(value) || value.matches(Regex(ReservedValuesConstants.NUMERIC_PATTERN))
    }
}