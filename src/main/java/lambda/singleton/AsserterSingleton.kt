package lambda.singleton

import lambda.asserter.*
import lambda.constants.FunctionLengthConstants
import lambda.constants.FunctionNameConstants
import lambda.constants.InvalidUserDefinedNameConstants
import lambda.constants.TokenValueConstants

enum class AsserterSingleton {
    INSTANCE;

    val functionLengthAsserter: FunctionLengthAsserter = FunctionLengthAsserter(
        DeterminerSingleton.INSTANCE.functionLengthDeterminer
    )

    val functionLengthMap: Map<String, Int> = mapOf(
        Pair(FunctionNameConstants.ATOM, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.CAR, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.CDR, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.CONS, FunctionLengthConstants.THREE),
        Pair(FunctionNameConstants.EQ, FunctionLengthConstants.THREE),
        Pair(FunctionNameConstants.GREATER, FunctionLengthConstants.THREE),
        Pair(FunctionNameConstants.INT, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.LESS, FunctionLengthConstants.THREE),
        Pair(FunctionNameConstants.MINUS, FunctionLengthConstants.THREE),
        Pair(FunctionNameConstants.NULL, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.PLUS, FunctionLengthConstants.THREE),
        Pair(FunctionNameConstants.QUOTE, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.TIMES, FunctionLengthConstants.THREE)
    )

    val expressionListLengthAsserter: ExpressionListLengthAsserter = ExpressionListLengthAsserter(
        functionLengthAsserter,
        functionLengthMap
    )

    val userDefinedFormalParametersAsserter: UserDefinedFormalParametersAsserter
    val userDefinedFunctionNameAsserter: UserDefinedFunctionNameAsserter
    val atomRootNodeAsserter: AtomRootNodeAsserter
    val inputFormatAsserter: InputFormatAsserter

    init {
        userDefinedFormalParametersAsserter = UserDefinedFormalParametersAsserter(
            InvalidUserDefinedNameConstants.InvalidNames
        )
        userDefinedFunctionNameAsserter = UserDefinedFunctionNameAsserter(
            DeterminerSingleton.INSTANCE.invalidNameDeterminer
        )
        atomRootNodeAsserter = AtomRootNodeAsserter(
            DeterminerSingleton.INSTANCE.numericStringDeterminer
        )
        inputFormatAsserter = InputFormatAsserter(
            TokenValueConstants.ERROR_STATE_PATTERN
        )
    }
}