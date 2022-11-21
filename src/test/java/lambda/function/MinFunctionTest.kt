package lambda.function

import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Node
import lambda.core.datamodels.Stack
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class MinFunctionTest {
    private val numericValueRetriever = Mockito.mock(NumericValueRetriever::class.java)

    private val params = Stack<Node>()

    private val minFunction = MinFunction(
        numericValueRetriever
    )

    @Test
    fun evaluateMinFunctionTest() {
        val first = Mockito.mock(Node::class.java)
        val second = Mockito.mock(Node::class.java)

        params.push(second)
        params.push(first)

        val firstNumeric = 100
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                first,
                FunctionNameConstants.MIN,
                1
            )
        ).thenReturn(firstNumeric)

        val secondNumeric = 10
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                second,
                FunctionNameConstants.MIN,
                2
            )
        ).thenReturn(secondNumeric)

        val actual = minFunction.evaluate(
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
                FunctionNameConstants.MIN,
                1
            )
        ).thenReturn(firstNumeric)

        val actual = minFunction.evaluate(
            params
        )

        Assertions.assertEquals(firstNumeric, (actual as AtomNode).value.toInt())
    }

}