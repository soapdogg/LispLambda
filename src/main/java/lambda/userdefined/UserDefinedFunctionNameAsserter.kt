package lambda.userdefined

import lambda.core.exceptions.InvalidUserDefinedNameException

internal interface UserDefinedFunctionNameAsserter {
    fun assertFunctionNameIsValid(functionName: String)
}

internal class UserDefinedFunctionNameAsserterImpl(
    private val invalidNameDeterminer: InvalidNameDeterminer
): UserDefinedFunctionNameAsserter {
    override fun assertFunctionNameIsValid(
        functionName: String
    ) {
        if (invalidNameDeterminer.isInvalidName(functionName)) throw InvalidUserDefinedNameException("Error! Invalid function name: $functionName\n")
    }
}