package lambda.parser

import lambda.core.constants.ReservedValuesConstants
import lambda.core.constants.TokenValueConstants
import lambda.core.datamodels.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class NodeParserTest {
    private val nodeParser = NodeParserImpl()

    @Test
    fun parseExpressionListNodeTest() {
        val openToken = TokenValueConstants.OPEN_PARENTHESES
        val openToken2 = TokenValueConstants.OPEN_PARENTHESES
        val literalToken = "value"
        val closeToken = TokenValueConstants.CLOSE_PARENTHESES
        val closeToken2 = TokenValueConstants.CLOSE_PARENTHESES

        val tokens = listOf(
            openToken,
            openToken2,
            literalToken,
            closeToken,
            closeToken2
        )

        val actual = nodeParser.parseIntoNode(
            tokens,
        )

        val resultingNodeExpressionListNode = actual as ExpressionListNode
        Assertions.assertEquals(2, resultingNodeExpressionListNode.children.size)
        val expressionListChild = resultingNodeExpressionListNode.children[0] as ExpressionListNode
        Assertions.assertEquals(2, expressionListChild.children.size)
        Assertions.assertEquals(literalToken, (expressionListChild.children[0] as AtomNode).value)
        Assertions.assertEquals(ReservedValuesConstants.NIL, (expressionListChild.children[1] as AtomNode).value)
        Assertions.assertEquals(ReservedValuesConstants.NIL, (resultingNodeExpressionListNode.children[1] as AtomNode).value)
    }

    @Test
    fun parseAtomNodeTest() {
        val literalToken = "value"

        val tokens = listOf(
            literalToken
        )

        val actual = nodeParser.parseIntoNode(
            tokens,
        )

        val actualAtomNode = actual as AtomNode
        Assertions.assertEquals(literalToken, actualAtomNode.value)
    }
}