package lambda.function

import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.ExpressionListNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.NodeV2
import lambda.function.internal.ListValueRetriever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class CdrFunctionTest {

    private val listValueRetriever = Mockito.mock(ListValueRetriever::class.java)

    private val params = Stack<NodeV2>()

    private val cdrFunction = CdrFunction(
        listValueRetriever
    )

    @Test
    fun listHasOneChildTest() {
        val first = Mockito.mock(NodeV2::class.java)
        params.push(first)

        val firstExpressionListNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(
            listValueRetriever.retrieveListValue(
                first,
                FunctionNameConstants.CDR
            )
        ).thenReturn(firstExpressionListNode)

        val child0 = Mockito.mock(NodeV2::class.java)
        val children = listOf(child0)
        Mockito.`when`(firstExpressionListNode.children).thenReturn(children)

        val actual = cdrFunction.evaluate(
            params
        )

        Assertions.assertEquals(child0, actual)
    }

    @Test
    fun listHasTwoChildrenTest() {
        val first = Mockito.mock(NodeV2::class.java)
        params.push(first)

        val firstExpressionListNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(
            listValueRetriever.retrieveListValue(
                first,
                FunctionNameConstants.CDR
            )
        ).thenReturn(firstExpressionListNode)

        val child0 = Mockito.mock(NodeV2::class.java)
        val child1 = Mockito.mock(NodeV2::class.java)
        val children = listOf(child0, child1)
        Mockito.`when`(firstExpressionListNode.children).thenReturn(children)

        val actual = cdrFunction.evaluate(
            params
        )

        Assertions.assertEquals(child1, actual)
    }

    @Test
    fun listHasMoreThanTwoChildrenTest() {
        val first = Mockito.mock(NodeV2::class.java)
        params.push(first)

        val firstExpressionListNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(
            listValueRetriever.retrieveListValue(
                first,
                FunctionNameConstants.CDR
            )
        ).thenReturn(firstExpressionListNode)

        val child0 = Mockito.mock(NodeV2::class.java)
        val child1 = Mockito.mock(NodeV2::class.java)
        val child2 = Mockito.mock(NodeV2::class.java)
        val children = listOf(child0, child1, child2)
        Mockito.`when`(firstExpressionListNode.children).thenReturn(children)


        val actual = cdrFunction.evaluate(
            params
        )

        val actualExpressionListNode = actual as ExpressionListNode
        Assertions.assertEquals(children.size - 1, actualExpressionListNode.children.size)
        Assertions.assertEquals(child1, actualExpressionListNode.children[0])
        Assertions.assertEquals(child2, actualExpressionListNode.children[1])
    }
}