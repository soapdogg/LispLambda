package lambda.singleton

import lambda.asserter.*
import lambda.core.constants.FunctionLengthConstants
import lambda.core.constants.FunctionNameConstants
import lambda.asserter.FunctionLengthDeterminer

enum class AsserterSingleton {
    INSTANCE;

    private val functionLengthDeterminer: FunctionLengthDeterminer = FunctionLengthDeterminer()

    val functionLengthAsserter: FunctionLengthAsserter = FunctionLengthAsserter(
        functionLengthDeterminer
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
}