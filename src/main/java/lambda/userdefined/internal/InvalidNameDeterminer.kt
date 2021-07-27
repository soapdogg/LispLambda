package lambda.userdefined.internal

import lambda.determiner.NumericStringDeterminer

class InvalidNameDeterminer(
    private val invalidNames: Set<String>,
    private val numericStringDeterminer: NumericStringDeterminer
) {
    fun isInvalidName(
        value: String
    ): Boolean {
        return invalidNames.contains(value) || numericStringDeterminer.isStringNumeric(value)
    }
}