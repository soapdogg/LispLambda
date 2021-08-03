package lambda.evaluator.rootnode.internal

import lambda.core.constants.ReservedValuesConstants
import lambda.core.datamodels.Node
import lambda.evaluator.rootnode.internal.NodeGenerator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class NodeGeneratorTest {
    private val nodeGenerator: NodeGenerator = NodeGenerator()


    @Test
    fun generateAtomNodeFromStringTest() {
        val value = "value"
        val (value1) = nodeGenerator.generateAtomNode(value)
        Assertions.assertEquals(value, value1)
    }

    @Test
    fun generateParserResultForExpressionListNodeTest() {
        val children = emptyList<Node>()

        val actual = nodeGenerator.generateExpressionListNode(children)

        Assertions.assertEquals(children, actual.children)
    }
}