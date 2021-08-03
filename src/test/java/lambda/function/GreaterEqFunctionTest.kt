package lambda.function

import lambda.core.constants.FunctionNameConstants
import lambda.core.constants.ReservedValuesConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Node
import lambda.core.datamodels.Stack
import lambda.function.internal.NumericValueRetriever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class GreaterEqFunctionTest {
    private val numericValueRetriever = Mockito.mock(NumericValueRetriever::class.java)

    private val params = Stack<Node>()

    private val greaterEqFunction = GreaterEqFunction(
        numericValueRetriever
    )

    @Test
    fun evaluateGreaterEqFunctionTest() {
        val first = Mockito.mock(Node::class.java)
        val second = Mockito.mock(Node::class.java)

        params.push(second)
        params.push(first)

        val firstNumeric = 10
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                first,
                FunctionNameConstants.GREATER_EQ,
                1
            )
        ).thenReturn(firstNumeric)

        val secondNumeric = 1
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                second,
                FunctionNameConstants.GREATER_EQ,
                2
            )
        ).thenReturn(secondNumeric)


        val actual = greaterEqFunction.evaluate(
            params
        )

        Assertions.assertEquals(ReservedValuesConstants.T, (actual as AtomNode).value)
    }

    @Test
    fun notGreaterEqThanTest() {
        val first = Mockito.mock(Node::class.java)
        val second = Mockito.mock(Node::class.java)

        params.push(second)
        params.push(first)

        val firstNumeric = 1
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                first,
                FunctionNameConstants.GREATER_EQ,
                1
            )
        ).thenReturn(firstNumeric)

        val secondNumeric = 10
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                second,
                FunctionNameConstants.GREATER_EQ,
                2
            )
        ).thenReturn(secondNumeric)


        val actual = greaterEqFunction.evaluate(
            params
        )

        Assertions.assertEquals(ReservedValuesConstants.NIL, (actual as AtomNode).value)

    }
}