package lambda.function

import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.Node
import lambda.function.internal.NumericValueRetriever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class TimesFunctionTest {
    private val numericValueRetriever = Mockito.mock(NumericValueRetriever::class.java)

    private val params = Stack<Node>()

    private val timesFunction = TimesFunction(
        numericValueRetriever
    )

    @Test
    fun evaluateTimesFunctionTest() {
        val first = Mockito.mock(Node::class.java)
        val second = Mockito.mock(Node::class.java)

        params.push(second)
        params.push(first)

        val firstNumeric = 10
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                first,
                FunctionNameConstants.TIMES,
                1
            )
        ).thenReturn(firstNumeric)

        val secondNumeric = 2
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                second,
                FunctionNameConstants.TIMES,
                2
            )
        ).thenReturn(secondNumeric)

        val result = (firstNumeric * secondNumeric).toString()

        val actual = timesFunction.evaluate(
            params
        )

        Assertions.assertEquals(result, (actual as AtomNode).value)
    }
}