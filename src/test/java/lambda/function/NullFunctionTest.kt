package lambda.function

import lambda.constants.ReservedValuesConstants
import lambda.datamodels.AtomNode
import lambda.datamodels.ExpressionListNode
import lambda.datamodels.Stack
import lambda.datamodels.NodeV2
import lambda.generator.NodeGenerator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class NullFunctionTest {

    private val nodeGenerator = Mockito.mock(NodeGenerator::class.java)

    private val params = Stack<NodeV2>()

    private val nullFunction = NullFunction(
        nodeGenerator
    )

    @Test
    fun evaluatedAtomIsNilTest() {
        val first = Mockito.mock(AtomNode::class.java)
        params.push(first)

        Mockito.`when`(first.value).thenReturn(ReservedValuesConstants.NIL)

        val resultingNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(nodeGenerator.generateAtomNode(true)).thenReturn(resultingNode)

        val actual = nullFunction.evaluate(
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

        val actual = nullFunction.evaluate(
            params
        )

        Assertions.assertEquals(resultingNode, actual)
    }
}