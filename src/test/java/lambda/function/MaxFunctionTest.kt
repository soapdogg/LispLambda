package lambda.function

import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Node
import lambda.core.datamodels.Stack
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class MaxFunctionTest {

    private val numericValueRetriever = Mockito.mock(NumericValueRetriever::class.java)

    private val params = Stack<Node>()

    private val maxFunction = MaxFunction(
        numericValueRetriever
    )

    @Test
    fun evaluateMaxFunctionTest() {
        val first = Mockito.mock(Node::class.java)
        val second = Mockito.mock(Node::class.java)

        params.push(second)
        params.push(first)

        val firstNumeric = 1
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                first,
                FunctionNameConstants.MAX,
                1
            )
        ).thenReturn(firstNumeric)

        val secondNumeric = 10
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                second,
                FunctionNameConstants.MAX,
                2
            )
        ).thenReturn(secondNumeric)

        val actual = maxFunction.evaluate(
            params
        )

        Assertions.assertEquals(secondNumeric.toString(), (actual as AtomNode).value)
    }

    @Test
    fun onlyOneElementTest() {
        val first = Mockito.mock(Node::class.java)

        params.push(first)

        val firstNumeric = 10
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                first,
                FunctionNameConstants.MAX,
                1
            )
        ).thenReturn(firstNumeric)

        val actual = maxFunction.evaluate(
            params
        )

        Assertions.assertEquals(firstNumeric, (actual as AtomNode).value.toInt())
    }
}