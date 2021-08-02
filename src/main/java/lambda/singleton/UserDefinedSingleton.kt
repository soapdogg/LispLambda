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
        FunctionNameConstants.EVEN_P,
        FunctionNameConstants.GCD,
        FunctionNameConstants.GREATER,
        FunctionNameConstants.GREATER_EQ,
        FunctionNameConstants.INT,
        FunctionNameConstants.LESS,
        FunctionNameConstants.LESS_EQ,
        FunctionNameConstants.MAX,
        FunctionNameConstants.MIN,
        FunctionNameConstants.MINUS,
        FunctionNameConstants.MINUS_P,
        FunctionNameConstants.NOT_EQ,
        FunctionNameConstants.NULL,
        FunctionNameConstants.ODD_P,
        FunctionNameConstants.PLUS,
        FunctionNameConstants.PLUS_P,
        FunctionNameConstants.QUOTE,
        FunctionNameConstants.TIMES,
        FunctionNameConstants.ZERO_P,
        ReservedValuesConstants.T,
        ReservedValuesConstants.NIL
    )

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