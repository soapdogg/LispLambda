package lambda.function

import lambda.core.constants.FunctionNameConstants
import lambda.core.constants.ReservedValuesConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Node
import lambda.core.datamodels.Stack
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class PlusPFunctionTest {

    private val numericValueRetriever = Mockito.mock(NumericValueRetriever::class.java)

    private val params = Stack<Node>()

    private val plusPFunction = PlusPFunction(
        numericValueRetriever
    )

    @Test
    fun evaluatePlusPFunctionTest(){
        val node = Mockito.mock(Node::class.java)

        params.push(node)

        val numeric = 10
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                node,
                FunctionNameConstants.PLUS_P,
                1
            )
        ).thenReturn(numeric)

        val actual = plusPFunction.evaluate(params)

        Assertions.assertEquals(ReservedValuesConstants.T, (actual as AtomNode).value)
    }
}