package lambda.singleton

import lambda.core.constants.ReservedValuesConstants
import lambda.userdefined.UserDefinedFunctionGenerator
import lambda.userdefined.internal.InvalidNameDeterminer
import lambda.userdefined.internal.UserDefinedFormalParametersAsserter
import lambda.userdefined.internal.UserDefinedFunctionNameAsserter

enum class UserDefinedSingleton {
    INSTANCE;

    private val builtInFunctionNames = FunctionSingleton.INSTANCE.functionMap.keys

    private val invalidNames = setOf(
        ReservedValuesConstants.T,
        ReservedValuesConstants.NIL
    ) + builtInFunctionNames

    private val userDefinedFormalParametersAsserter = UserDefinedFormalParametersAsserter(
        invalidNames
    )
    private val invalidNameDeterminer = InvalidNameDeterminer(
        invalidNames
    )
    private val userDefinedFunctionNameAsserter = UserDefinedFunctionNameAsserter(
        invalidNameDeterminer
    )

    val userDefinedFunctionGenerator: UserDefinedFunctionGenerator = UserDefinedFunctionGenerator(
        AsserterSingleton.INSTANCE.functionLengthAsserter,
        userDefinedFunctionNameAsserter,
        userDefinedFormalParametersAsserter
    )
}