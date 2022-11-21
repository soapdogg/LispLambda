package lambda.function

import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Node
import lambda.core.datamodels.Stack
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class OnePlusFunctionTest {

    private val numericValueRetriever = Mockito.mock(NumericValueRetriever::class.java)

    private val params = Stack<Node>()

    private val onePlusFunction = OnePlusFunction(
        numericValueRetriever
    )

    @Test
    fun evaluateTest() {
        val node = Mockito.mock(Node::class.java)

        params.push(node)

        val numeric = 3
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                node,
                FunctionNameConstants.ONE_PLUS,
                1
            )
        ).thenReturn(numeric)

        val expected = (numeric + 1).toString()
        val actual = onePlusFunction.evaluate(params)

        Assertions.assertEquals(expected, (actual as AtomNode).value)
    }
}