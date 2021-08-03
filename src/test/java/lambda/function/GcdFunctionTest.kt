package lambda.function

import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Node
import lambda.core.datamodels.Stack
import lambda.function.internal.GcdCalculator
import lambda.function.internal.NumericValueRetriever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class GcdFunctionTest {

    private val numericValueRetriever = Mockito.mock(NumericValueRetriever::class.java)
    private val gcdCalculator = Mockito.mock(GcdCalculator::class.java)

    private val params = Stack<Node>()

    private val gcdFunction = GcdFunction(
        numericValueRetriever,
        gcdCalculator
    )

    @Test
    fun evaluateEmptyTest() {

        val actual = gcdFunction.evaluate(params)

        Assertions.assertEquals(0, (actual as AtomNode).value.toInt())
    }

    @Test
    fun evaluateOneElementTest() {
        val first = Mockito.mock(Node::class.java)

        params.push(first)

        val firstNumeric = -10
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                first,
                FunctionNameConstants.GCD,
                1
            )
        ).thenReturn(firstNumeric)

        val actual = gcdFunction.evaluate(params)

        Assertions.assertEquals(kotlin.math.abs(firstNumeric), (actual as AtomNode).value.toInt())
    }

    @Test
    fun evaluateMoreThanOneElementTest() {

        val first = Mockito.mock(Node::class.java)
        val second = Mockito.mock(Node::class.java)

        params.push(second)
        params.push(first)

        val firstNumeric = 81
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                first,
                FunctionNameConstants.GCD,
                1
            )
        ).thenReturn(firstNumeric)

        val secondNumeric = 153
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                second,
                FunctionNameConstants.GCD,
                2
            )
        ).thenReturn(secondNumeric)

        val gcd = 9
        Mockito.`when`(
            gcdCalculator.calculateGCD(firstNumeric, secondNumeric)
        ).thenReturn(gcd)

        val actual = gcdFunction.evaluate(
            params
        )

        Assertions.assertEquals(gcd, (actual as AtomNode).value.toInt())
    }
}