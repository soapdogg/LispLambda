package lambda.function

import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Node
import lambda.core.datamodels.Stack
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class LcmFunctionTest {

    private val numericValueRetriever = Mockito.mock(NumericValueRetriever::class.java)
    private val gcdCalculator = Mockito.mock(GcdCalculator::class.java)

    private val params = Stack<Node>()

    private val lcmFunction = LcmFunction(
        numericValueRetriever,
        gcdCalculator
    )

    @Test
    fun evaluateOneElementTest() {
        val first = Mockito.mock(Node::class.java)

        params.push(first)

        val firstNumeric = -10
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                first,
                FunctionNameConstants.LCM,
                1
            )
        ).thenReturn(firstNumeric)

        val actual = lcmFunction.evaluate(params)

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
                FunctionNameConstants.LCM,
                1
            )
        ).thenReturn(firstNumeric)

        val secondNumeric = 153
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                second,
                FunctionNameConstants.LCM,
                2
            )
        ).thenReturn(secondNumeric)

        val gcd = 9
        Mockito.`when`(
            gcdCalculator.calculateGCD(firstNumeric, secondNumeric)
        ).thenReturn(gcd)

        val lcm = (firstNumeric * secondNumeric) / gcd

        val actual = lcmFunction.evaluate(
            params
        )

        Assertions.assertEquals(lcm, (actual as AtomNode).value.toInt())
    }
}