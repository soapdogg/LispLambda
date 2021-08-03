package lambda.function

import lambda.core.constants.FunctionNameConstants
import lambda.core.constants.ReservedValuesConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.Node
import lambda.function.internal.NumericValueRetriever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class GreaterFunctionTest {
    private val numericValueRetriever = Mockito.mock(NumericValueRetriever::class.java)

    private val params = Stack<Node>()

    private val greaterFunction = GreaterFunction(
        numericValueRetriever
    )

    @Test
    fun evaluateGreaterFunctionTest() {
        val first = Mockito.mock(Node::class.java)
        val second = Mockito.mock(Node::class.java)

        params.push(second)
        params.push(first)

        val firstNumeric = 10
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                first,
                FunctionNameConstants.GREATER,
                1
            )
        ).thenReturn(firstNumeric)

        val secondNumeric = 1
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                second,
                FunctionNameConstants.GREATER,
                2
            )
        ).thenReturn(secondNumeric)


        val actual = greaterFunction.evaluate(
            params
        )

        Assertions.assertEquals(ReservedValuesConstants.T, (actual as AtomNode).value)
    }

    @Test
    fun notGreaterThanTest() {
        val first = Mockito.mock(Node::class.java)
        val second = Mockito.mock(Node::class.java)

        params.push(second)
        params.push(first)

        val firstNumeric = 1
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                first,
                FunctionNameConstants.GREATER,
                1
            )
        ).thenReturn(firstNumeric)

        val secondNumeric = 10
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                second,
                FunctionNameConstants.GREATER,
                2
            )
        ).thenReturn(secondNumeric)


        val actual = greaterFunction.evaluate(
            params
        )

        Assertions.assertEquals(ReservedValuesConstants.NIL, (actual as AtomNode).value)
    }
}