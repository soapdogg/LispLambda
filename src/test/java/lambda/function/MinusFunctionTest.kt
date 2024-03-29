package lambda.function

import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.Node
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class MinusFunctionTest {
    private val numericValueRetriever = Mockito.mock(NumericValueRetriever::class.java)

    private val params = Stack<Node>()

    private val minusFunction = MinusFunction(
        numericValueRetriever
    )

    @Test
    fun evaluateMinusFunctionTest() {
        val first = Mockito.mock(Node::class.java)
        val second = Mockito.mock(Node::class.java)

        params.push(second)
        params.push(first)

        val firstNumeric = 10
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                first,
                FunctionNameConstants.MINUS,
                1
            )
        ).thenReturn(firstNumeric)

        val secondNumeric = 1
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                second,
                FunctionNameConstants.MINUS,
                2
            )
        ).thenReturn(secondNumeric)

        val result = firstNumeric - secondNumeric

        val actual = minusFunction.evaluate(
            params
        )

        Assertions.assertEquals(result.toString(), (actual as AtomNode).value)
    }

    @Test
    fun onlyOneElementTest() {
        val first = Mockito.mock(Node::class.java)

        params.push(first)

        val firstNumeric = 10
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                first,
                FunctionNameConstants.MINUS,
                1
            )
        ).thenReturn(firstNumeric)

        val actual = minusFunction.evaluate(
            params
        )

        Assertions.assertEquals(-firstNumeric, (actual as AtomNode).value.toInt())
    }
}