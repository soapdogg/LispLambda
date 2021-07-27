package lambda.function

import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.ExpressionListNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.NodeV2
import lambda.function.internal.NodeGenerator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class IntFunctionTest {
    private val nodeGenerator = Mockito.mock(NodeGenerator::class.java)

    private val params = Stack<NodeV2>()

    private val intFunction = IntFunction(
        nodeGenerator
    )

    @Test
    fun evaluatedAtomIsNumericTest() {
        val first = Mockito.mock(AtomNode::class.java)
        params.push(first)

        val numeric = 10
        Mockito.`when`(first.value).thenReturn(numeric.toString())

        val isNumeric = true

        val resultingNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(nodeGenerator.generateAtomNode(isNumeric)).thenReturn(resultingNode)

        val actual = intFunction.evaluate(
            params
        )

        Assertions.assertEquals(resultingNode, actual)
    }

    @Test
    fun firstIsExpressionListTest() {
        val first = Mockito.mock(ExpressionListNode::class.java)
        params.push(first)

        val resultingNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(nodeGenerator.generateAtomNode(false)).thenReturn(resultingNode)

        val actual = intFunction.evaluate(
            params
        )

        Assertions.assertEquals(resultingNode, actual)
    }
}