package lambda.singleton

import lambda.asserter.*
import lambda.core.constants.FunctionLengthConstants
import lambda.core.constants.FunctionNameConstants
import lambda.asserter.FunctionLengthDeterminer
import kotlin.math.min

enum class AsserterSingleton {
    INSTANCE;

    private val functionLengthDeterminer = FunctionLengthDeterminer()

    val functionLengthAsserter = FunctionLengthAsserter(
        functionLengthDeterminer
    )

    private val functionLengthMap = mapOf(
        Pair(FunctionNameConstants.ATOM, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.CAR, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.CDR, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.CONS, FunctionLengthConstants.THREE),
        Pair(FunctionNameConstants.EVEN_P, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.EXP, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.EXPT, FunctionLengthConstants.THREE),
        Pair(FunctionNameConstants.INT, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.MINUS_P, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.NULL, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.ODD_P, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.ONE_MINUS, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.ONE_PLUS, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.PLUS_P, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.QUOTE, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.ZERO_P, FunctionLengthConstants.TWO)
    )

    private val minimumFunctionLengthMap = mapOf(
        Pair(FunctionNameConstants.EQ, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.GREATER, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.GREATER_EQ, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.LCM, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.LESS, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.LESS_EQ, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.MAX, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.MIN, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.MINUS, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.NOT_EQ, FunctionLengthConstants.TWO)
    )

    val expressionListLengthAsserter: ExpressionListLengthAsserter = ExpressionListLengthAsserter(
        functionLengthAsserter,
        functionLengthMap,
        minimumFunctionLengthMap
    )
}