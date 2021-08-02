package lambda.parser.internal

import lambda.core.datamodels.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class NodeParserTest {
    private val expressionListNodeParser = Mockito.mock(ExpressionListNodeParser::class.java)
    private val nodeParser = NodeParser(
        expressionListNodeParser
    )

    @Test
    fun parseIntoNodeTest() {
        val headToken = Mockito.mock(Token::class.java)
        val tokens = listOf(headToken)
        Mockito.`when`(headToken.tokenKind).thenReturn(TokenKind.OPEN_TOKEN)

        val expected = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(expressionListNodeParser.parseExpressionListNode(tokens)).thenReturn(expected)

        val actual = nodeParser.parseIntoNode(tokens)
        Assertions.assertEquals(expected, actual)
    }
}