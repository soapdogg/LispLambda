package lambda.function

import lambda.core.constants.FunctionNameConstants
import lambda.core.constants.ReservedValuesConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.NodeV2
import lambda.core.datamodels.Stack
import lambda.function.internal.NumericValueRetriever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class ZeroPFunctionTest {

    private val numericValueRetriever = Mockito.mock(NumericValueRetriever::class.java)

    private val params = Stack<NodeV2>()

    private val zeroPFunction = ZeroPFunction(
        numericValueRetriever
    )

    @Test
    fun evaluateZeroPFunctionTest(){
        val node = Mockito.mock(NodeV2::class.java)

        params.push(node)

        val numeric = 10
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                node,
                FunctionNameConstants.ZERO_P,
                1
            )
        ).thenReturn(numeric)

        val actual = zeroPFunction.evaluate(params)

        Assertions.assertEquals(ReservedValuesConstants.NIL, (actual as AtomNode).value)
    }
}