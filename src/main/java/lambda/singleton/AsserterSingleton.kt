package lambda.singleton

import lambda.asserter.*
import lambda.core.constants.FunctionLengthConstants
import lambda.core.constants.FunctionNameConstants
import lambda.asserter.FunctionLengthDeterminer
import kotlin.math.min

enum class AsserterSingleton {
    INSTANCE;

    private val functionLengthDeterminer: FunctionLengthDeterminer = FunctionLengthDeterminer()

    val functionLengthAsserter: FunctionLengthAsserter = FunctionLengthAsserter(
        functionLengthDeterminer
    )

    private val functionLengthMap: Map<String, Int> = mapOf(
        Pair(FunctionNameConstants.ATOM, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.CAR, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.CDR, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.CONS, FunctionLengthConstants.THREE),
        Pair(FunctionNameConstants.INT, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.NULL, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.QUOTE, FunctionLengthConstants.TWO),
    )

    private val minimumFunctionLengthMap: Map<String, Int> = mapOf(
        Pair(FunctionNameConstants.EQ, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.GREATER, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.LCM, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.LESS, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.MINUS, FunctionLengthConstants.TWO)
    )

    val expressionListLengthAsserter: ExpressionListLengthAsserter = ExpressionListLengthAsserter(
        functionLengthAsserter,
        functionLengthMap,
        minimumFunctionLengthMap
    )
}