package lambda.userdefined

import lambda.Function
import lambda.FunctionLengthAsserter
import lambda.UserDefinedFunctionGenerator
import lambda.core.constants.ReservedValuesConstants

enum class UserDefinedSingleton {
    INSTANCE;

    fun getUserDefinedFunctionGenerator(
        functionMap: Map<String, Function>,
        functionLengthAsserter: FunctionLengthAsserter
    ): UserDefinedFunctionGenerator {
        val builtInFunctionNames = functionMap.keys

        val invalidNames = setOf(
            ReservedValuesConstants.T,
            ReservedValuesConstants.NIL
        ) + builtInFunctionNames

        val userDefinedFormalParametersAsserter = UserDefinedFormalParametersAsserterImpl(invalidNames)
        val invalidNameDeterminer = InvalidNameDeterminerImpl(invalidNames)
        val userDefinedFunctionNameAsserter = UserDefinedFunctionNameAsserterImpl(invalidNameDeterminer)

        return UserDefinedFunctionGeneratorImpl(
            functionLengthAsserter,
            userDefinedFunctionNameAsserter,
            userDefinedFormalParametersAsserter
        )
    }
}