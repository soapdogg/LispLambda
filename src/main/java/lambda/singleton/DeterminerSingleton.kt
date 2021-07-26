package lambda.singleton

import lambda.constants.InvalidUserDefinedNameConstants
import lambda.determiner.*
import lambda.tokenizer.internal.LiteralTokenValueEndIndexDeterminer
import lambda.tokenizer.internal.NumericTokenValueEndIndexDeterminer

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