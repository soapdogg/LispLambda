package lambda.parser

import lambda.core.constants.TokenValueConstants
import lambda.core.datamodels.Node
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
        val headToken = "token"
        val tokens = listOf(headToken)

        val resultingNode = Mockito.mock(Node::class.java)
        Mockito.`when`(nodeParser.parseIntoNode(tokens)).thenReturn(resultingNode)

        val actual = rootParser.parse(tokens)
        Assertions.assertEquals(1, actual.size)
        Assertions.assertEquals(resultingNode, actual[0])
    }

    @Test
    fun tooManyOpenTokenTest() {
        val headToken = TokenValueConstants.OPEN_PARENTHESES
        val tokens = listOf(headToken)

        Assertions.assertThrows(
            UnexpectedTokenKindException::class.java
        ) {rootParser.parse(tokens)}

        Mockito.verifyNoInteractions(nodeParser)
    }

    @Test
    fun tooManyCloseTokenTest() {
        val headToken = TokenValueConstants.CLOSE_PARENTHESES

        val tokens = listOf(headToken)

        Assertions.assertThrows(
            UnexpectedTokenKindException::class.java
        ) {rootParser.parse(tokens)}

        Mockito.verifyNoInteractions(nodeParser)
    }
}