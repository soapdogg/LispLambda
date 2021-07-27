package lambda.singleton

import lambda.core.constants.InvalidUserDefinedNameConstants
import lambda.determiner.*

enum class DeterminerSingleton {
    INSTANCE;

    val numericStringDeterminer: NumericStringDeterminer = NumericStringDeterminer()
    val functionLengthDeterminer: FunctionLengthDeterminer = FunctionLengthDeterminer()
    val invalidNameDeterminer: InvalidNameDeterminer


    init {
        invalidNameDeterminer = InvalidNameDeterminer(
            InvalidUserDefinedNameConstants.InvalidNames,
            numericStringDeterminer
        )
    }
}