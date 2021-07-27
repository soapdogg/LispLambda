package lambda.function

import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.NodeV2
import lambda.function.internal.NumericValueRetriever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class PlusFunctionTest {
    private val numericValueRetriever = Mockito.mock(NumericValueRetriever::class.java)

    private val params = Stack<NodeV2>()

    private val plusFunction = PlusFunction(
        numericValueRetriever
    )

    @Test
    fun evaluatePlusFunctionTest() {
        val first = Mockito.mock(NodeV2::class.java)
        val second = Mockito.mock(NodeV2::class.java)

        params.push(second)
        params.push(first)

        val firstNumeric = 10
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                first,
                FunctionNameConstants.PLUS,
                1
            )
        ).thenReturn(firstNumeric)

        val secondNumeric = 1
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                second,
                FunctionNameConstants.PLUS,
                2
            )
        ).thenReturn(secondNumeric)

        val result = (firstNumeric + secondNumeric).toString()
        val actual = plusFunction.evaluate(
            params
        )

        Assertions.assertEquals(result, (actual as AtomNode).value)
    }
}