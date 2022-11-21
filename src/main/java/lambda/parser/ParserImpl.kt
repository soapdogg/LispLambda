package lambda.parser

import lambda.Parser
import lambda.core.constants.TokenValueConstants
import lambda.core.datamodels.Node
import lambda.core.exceptions.UnexpectedTokenKindException
import kotlin.collections.ArrayList

internal class ParserImpl (
  private val nodeParser: NodeParser
): Parser {

    override fun parse(tokens: List<String>): List<Node> {
        var openClose = 0
        val listOfLists: MutableList<MutableList<String>> = ArrayList()

        var currentList: MutableList<String> = ArrayList()
        tokens.forEach {
            currentList.add(it)
            if (it == TokenValueConstants.OPEN_PARENTHESES) openClose++
            else if (it == TokenValueConstants.CLOSE_PARENTHESES) openClose--
            if (openClose < 0) {
                val errorMessage = """Expected either an ATOM or OPEN token.${'\n'}Actual Value: ${it}${'\n'}"""
                throw UnexpectedTokenKindException(errorMessage)
            }
            if (openClose == 0) {
                listOfLists.add(currentList)
                currentList = ArrayList()
            }
        }

        if (openClose > 0) {
            val errorMessage = "Expected a token.\nActual: null\n"
            throw UnexpectedTokenKindException(errorMessage)
        }

        return listOfLists.map { nodeParser.parseIntoNode(it) }
    }
}