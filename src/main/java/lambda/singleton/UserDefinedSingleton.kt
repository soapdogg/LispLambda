package lambda.singleton

import lambda.core.constants.FunctionNameConstants
import lambda.core.constants.ReservedValuesConstants
import lambda.userdefined.UserDefinedFunctionGenerator
import lambda.userdefined.internal.InvalidNameDeterminer
import lambda.userdefined.internal.UserDefinedFormalParametersAsserter
import lambda.userdefined.internal.UserDefinedFunctionNameAsserter

enum class UserDefinedSingleton {
    INSTANCE;

    private val invalidNames = setOf(
        FunctionNameConstants.ATOM,
        FunctionNameConstants.CAR,
        FunctionNameConstants.CDR,
        FunctionNameConstants.COND,
        FunctionNameConstants.CONS,
        FunctionNameConstants.DEFUN,
        FunctionNameConstants.EQ,
        FunctionNameConstants.GREATER,
        FunctionNameConstants.INT,
        FunctionNameConstants.LESS,
        FunctionNameConstants.MINUS,
        FunctionNameConstants.NULL,
        FunctionNameConstants.PLUS,
        FunctionNameConstants.QUOTE,
        FunctionNameConstants.TIMES,
        ReservedValuesConstants.T,
        ReservedValuesConstants.NIL
    )

    private val userDefinedFormalParametersAsserter = UserDefinedFormalParametersAsserter(
        invalidNames
    )
    private val invalidNameDeterminer = InvalidNameDeterminer(
        invalidNames,
        DeterminerSingleton.INSTANCE.numericStringDeterminer
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