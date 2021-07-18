package lambda.function

import lambda.datamodels.AtomNode
import lambda.datamodels.ExpressionListNode
import lambda.datamodels.Stack
import lambda.datamodels.NodeV2
import lambda.determiner.NumericStringDeterminer
import lambda.generator.NodeGenerator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class IntFunctionTest {
    private val numericStringDeterminer = Mockito.mock(NumericStringDeterminer::class.java)
    private val nodeGenerator = Mockito.mock(NodeGenerator::class.java)

    private val params = Stack<NodeV2>()

    private val intFunction = IntFunction(
        numericStringDeterminer,
        nodeGenerator
    )

    @Test
    fun evaluatedAtomIsNumericTest() {
        val first = Mockito.mock(AtomNode::class.java)
        params.push(first)

        val numeric = 10
        Mockito.`when`(first.value).thenReturn(numeric.toString())

        val isNumeric = true
        Mockito.`when`(numericStringDeterminer.isStringNumeric(numeric.toString())).thenReturn(isNumeric)

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