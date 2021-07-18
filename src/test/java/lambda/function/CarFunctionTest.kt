package lambda.function

import lambda.constants.FunctionNameConstants
import lambda.datamodels.ExpressionListNode
import lambda.datamodels.Stack
import lambda.datamodels.NodeV2
import lambda.valueretriver.ListValueRetriever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class CarFunctionTest {

    private val listValueRetriever = Mockito.mock(ListValueRetriever::class.java)

    private val params = Stack<NodeV2>()

    private val carFunction = CarFunction(
        listValueRetriever
    )

    @Test
    fun evaluateCarFunctionTest() {
        val first = Mockito.mock(NodeV2::class.java)
        params.push(first)

        val firstExpressionListNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(
            listValueRetriever.retrieveListValue(
                first,
                FunctionNameConstants.CAR
            )
        ).thenReturn(firstExpressionListNode)

        val child0 = Mockito.mock(NodeV2::class.java)
        val children = listOf(child0)
        Mockito.`when`(firstExpressionListNode.children).thenReturn(children)

        val actual = carFunction.evaluate(
            params
        )

        Assertions.assertEquals(child0, actual)
    }
}