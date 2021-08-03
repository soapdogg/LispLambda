package lambda.function

import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.ExpressionListNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.Node
import lambda.function.internal.ListValueRetriever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class CarFunctionTest {

    private val listValueRetriever = Mockito.mock(ListValueRetriever::class.java)

    private val params = Stack<Node>()

    private val carFunction = CarFunction(
        listValueRetriever
    )

    @Test
    fun evaluateCarFunctionTest() {
        val first = Mockito.mock(Node::class.java)
        params.push(first)

        val firstExpressionListNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(
            listValueRetriever.retrieveListValue(
                first,
                FunctionNameConstants.CAR
            )
        ).thenReturn(firstExpressionListNode)

        val child0 = Mockito.mock(Node::class.java)
        val children = listOf(child0)
        Mockito.`when`(firstExpressionListNode.children).thenReturn(children)

        val actual = carFunction.evaluate(
            params
        )

        Assertions.assertEquals(child0, actual)
    }
}