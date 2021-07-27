package lambda.function

import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.ExpressionListNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.NodeV2
import lambda.generator.NodeGenerator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class EqFunctionTest {

    private val nodeGenerator = Mockito.mock(NodeGenerator::class.java)

    private val params = Stack<NodeV2>()

    private val eqFunction = EqFunction(
        nodeGenerator
    )

    @Test
    fun atomNodeTest() {
        val first = Mockito.mock(AtomNode::class.java)
        val second = Mockito.mock(AtomNode::class.java)

        params.push(second)
        params.push(first)

        val resultingNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(
            nodeGenerator.generateAtomNode(false)
        ).thenReturn(resultingNode)

        val actual = eqFunction.evaluate(
            params
        )

        Assertions.assertEquals(resultingNode, actual)
    }

    @Test
    fun expressionNodeTest() {
        val first = Mockito.mock(ExpressionListNode::class.java)
        val second = Mockito.mock(ExpressionListNode::class.java)

        params.push(second)
        params.push(first)

        val resultingNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(
            nodeGenerator.generateAtomNode(false)
        ).thenReturn(resultingNode)

        val actual = eqFunction.evaluate(
            params
        )

        Assertions.assertEquals(resultingNode, actual)
    }
}