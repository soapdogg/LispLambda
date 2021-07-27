package lambda.function

import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.ExpressionListNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.NodeV2
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class ConsFunctionTest {

    private val params = Stack<NodeV2>()

    private val consFunction = ConsFunction()

    @Test
    fun atomNodeTest() {
        val first = Mockito.mock(AtomNode::class.java)
        val second = Mockito.mock(AtomNode::class.java)

        params.push(second)
        params.push(first)

        val actual = consFunction.evaluate(
            params
        )

        val actualExpressionListNode = actual as ExpressionListNode
        Assertions.assertEquals(2, actualExpressionListNode.children.size)
        Assertions.assertEquals(first, actualExpressionListNode.children[0])
        Assertions.assertEquals(second, actualExpressionListNode.children[1])
    }

    @Test
    fun expressionListNodeTest() {
        val first = Mockito.mock(ExpressionListNode::class.java)
        val second = Mockito.mock(ExpressionListNode::class.java)

        params.push(second)
        params.push(first)

        val dataChild0 = Mockito.mock(NodeV2::class.java)
        val dataChild1 = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(
            second.children
        ).thenReturn(listOf(dataChild0, dataChild1))


        val actual = consFunction.evaluate(
            params
        )

        val actualExpressionListNode = actual as ExpressionListNode
        Assertions.assertEquals(3, actualExpressionListNode.children.size)
        Assertions.assertEquals(first, actualExpressionListNode.children[0])
        Assertions.assertEquals(dataChild0, actualExpressionListNode.children[1])
        Assertions.assertEquals(dataChild1, actualExpressionListNode.children[2])
    }
}