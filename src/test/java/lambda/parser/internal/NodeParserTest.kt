package lambda.parser.internal

import lambda.core.constants.ReservedValuesConstants
import lambda.core.datamodels.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class NodeParserTest {
    private val nodeParser = NodeParser()

    @Test
    fun parseExpressionListNodeTest() {
        val openToken = Mockito.mock(Token::class.java)
        Mockito.`when`(openToken.tokenKind).thenReturn(TokenKind.OPEN_TOKEN)

        val openToken2 = Mockito.mock(Token::class.java)
        Mockito.`when`(openToken2.tokenKind).thenReturn(TokenKind.OPEN_TOKEN)

        val literalToken = Mockito.mock(Token::class.java)
        Mockito.`when`(literalToken.tokenKind).thenReturn(TokenKind.LITERAL_TOKEN)

        val value = "value"
        Mockito.`when`(literalToken.value).thenReturn(value)

        val closeToken = Mockito.mock(Token::class.java)
        Mockito.`when`(closeToken.tokenKind).thenReturn(TokenKind.CLOSE_TOKEN)

        val closeToken2 = Mockito.mock(Token::class.java)
        Mockito.`when`(closeToken2.tokenKind).thenReturn(TokenKind.CLOSE_TOKEN)

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
        Assertions.assertEquals(value, (expressionListChild.children[0] as AtomNode).value)
        Assertions.assertEquals(ReservedValuesConstants.NIL, (expressionListChild.children[1] as AtomNode).value)
        Assertions.assertEquals(ReservedValuesConstants.NIL, (resultingNodeExpressionListNode.children[1] as AtomNode).value)
    }

    @Test
    fun parseAtomNodeTest() {
        val literalToken = Mockito.mock(Token::class.java)
        Mockito.`when`(literalToken.tokenKind).thenReturn(TokenKind.LITERAL_TOKEN)

        val value = "value"
        Mockito.`when`(literalToken.value).thenReturn(value)

        val tokens = listOf(
            literalToken
        )

        val actual = nodeParser.parseIntoNode(
            tokens,
        )

        val actualAtomNode = actual as AtomNode
        Assertions.assertEquals(value, actualAtomNode.value)
    }
}