package lambda.userdefined.internal

import lambda.core.exceptions.InvalidUserDefinedNameException

class UserDefinedFunctionNameAsserter(
    private val invalidNameDeterminer: InvalidNameDeterminer
) {
    fun assertFunctionNameIsValid(
        functionName: String
    ) {
        if (invalidNameDeterminer.isInvalidName(functionName)) throw InvalidUserDefinedNameException("Error! Invalid function name: $functionName\n")
    }
}