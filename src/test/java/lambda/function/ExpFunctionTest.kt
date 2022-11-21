package lambda.function

import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Node
import lambda.core.datamodels.Stack
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class ExpFunctionTest {

    private val numericValueRetriever = Mockito.mock(NumericValueRetriever::class.java)

    private val params = Stack<Node>()

    private val expFunction = ExpFunction(
        numericValueRetriever
    )

    @Test
    fun evaluateExpFunctionTest(){
        val node = Mockito.mock(Node::class.java)

        params.push(node)

        val numeric = 3
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                node,
                FunctionNameConstants.EXP,
                1
            )
        ).thenReturn(numeric)

        val expected = kotlin.math.exp(3.0).toInt().toString()
        val actual = expFunction.evaluate(params)


        Assertions.assertEquals(expected, (actual as AtomNode).value)
    }
}