package lambda.parser.internal

import lambda.core.constants.ReservedValuesConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.ExpressionListNode
import lambda.core.datamodels.Token
import lambda.core.datamodels.TokenKind
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class ExpressionListNodeParserTest {

    private val expressionListNodeParser = ExpressionListNodeParser()

    @Test
    fun parseExpressionListNodeTest() {
        val startingPoint = 0

        val openToken = Mockito.mock(Token::class.java)
        Mockito.`when`(openToken.tokenKind).thenReturn(TokenKind.OPEN_TOKEN)

        val literalToken = Mockito.mock(Token::class.java)
        Mockito.`when`(literalToken.tokenKind).thenReturn(TokenKind.LITERAL_TOKEN)

        val value = "value"
        Mockito.`when`(literalToken.value).thenReturn(value)

        val closeToken = Mockito.mock(Token::class.java)
        Mockito.`when`(closeToken.tokenKind).thenReturn(TokenKind.CLOSE_TOKEN)

        val tokens = listOf(
            openToken,
            literalToken,
            closeToken
        )

        val actual = expressionListNodeParser.parseExpressionListNode(
            tokens,
            startingPoint
        )

        val resultingNodeExpressionListNode = actual.resultingNode
        Assertions.assertEquals(1, resultingNodeExpressionListNode.children.size)
        val child = resultingNodeExpressionListNode.children[0] as ExpressionListNode
        Assertions.assertEquals(2, child.children.size)
        Assertions.assertEquals(value, (child.children[0] as AtomNode).value)
        Assertions.assertEquals(ReservedValuesConstants.NIL, (child.children[1] as AtomNode).value)
        Assertions.assertEquals(3, actual.nextIndex)
    }
}