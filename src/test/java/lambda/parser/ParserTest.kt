package lambda.parser

import lambda.core.datamodels.NodeV2
import lambda.core.datamodels.Token
import lambda.core.datamodels.TokenKind
import lambda.core.exceptions.UnexpectedTokenKindException
import lambda.parser.internal.NodeParser
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class ParserTest {
    private val nodeParser = Mockito.mock(NodeParser::class.java)
    private val rootParser = Parser(nodeParser)

    @Test
    fun rootParserTest() {
        val headToken = Mockito.mock(Token::class.java)
        val tokens = listOf(headToken)

        val resultingNode = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(nodeParser.parseIntoNode(tokens)).thenReturn(resultingNode)

        val actual = rootParser.parse(tokens)
        Assertions.assertEquals(1, actual.size)
        Assertions.assertEquals(resultingNode, actual[0])
    }

    @Test
    fun tooManyOpenTokenTest() {
        val headToken = Mockito.mock(Token::class.java)
        Mockito.`when`(headToken.tokenKind).thenReturn(TokenKind.OPEN_TOKEN)

        val tokens = listOf(headToken)

        Assertions.assertThrows(
            UnexpectedTokenKindException::class.java
        ) {rootParser.parse(tokens)}

        Mockito.verifyNoInteractions(nodeParser)
    }

    @Test
    fun tooManyCloseTokenTest() {
        val headToken = Mockito.mock(Token::class.java)
        Mockito.`when`(headToken.tokenKind).thenReturn(TokenKind.CLOSE_TOKEN)

        val tokens = listOf(headToken)

        Assertions.assertThrows(
            UnexpectedTokenKindException::class.java
        ) {rootParser.parse(tokens)}

        Mockito.verifyNoInteractions(nodeParser)
    }
}