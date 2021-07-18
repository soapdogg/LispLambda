package lambda.singleton

import lambda.constants.InvalidUserDefinedNameConstants
import lambda.determiner.*

enum class DeterminerSingleton {
    INSTANCE;

    val numericStringDeterminer: NumericStringDeterminer = NumericStringDeterminer()
    val functionLengthDeterminer: FunctionLengthDeterminer = FunctionLengthDeterminer()
    val invalidNameDeterminer: InvalidNameDeterminer
    val literalTokenValueEndIndexDeterminer: LiteralTokenValueEndIndexDeterminer
    val numericTokenValueEndIndexDeterminer: NumericTokenValueEndIndexDeterminer

    init {
        invalidNameDeterminer = InvalidNameDeterminer(
            InvalidUserDefinedNameConstants.InvalidNames,
            numericStringDeterminer
        )
        literalTokenValueEndIndexDeterminer = LiteralTokenValueEndIndexDeterminer()
        numericTokenValueEndIndexDeterminer = NumericTokenValueEndIndexDeterminer()
    }
}