package lambda.parser

import lambda.core.datamodels.NodeV2
import lambda.core.datamodels.Token
import lambda.core.datamodels.TokenKind
import lambda.core.exceptions.UnexpectedTokenKindException
import lambda.parser.internal.NodeParser
import kotlin.collections.ArrayList

class Parser (
  private val nodeParser: NodeParser
) {

    fun parse(tokens: List<Token>): List<NodeV2> {
        var openClose = 0
        val listOfLists: MutableList<MutableList<Token>> = ArrayList()

        var currentList: MutableList<Token> = ArrayList()
        tokens.forEach {
            currentList.add(it)
            if (it.tokenKind == TokenKind.OPEN_TOKEN) openClose++
            else if (it.tokenKind == TokenKind.CLOSE_TOKEN) openClose--
            if (openClose < 0) {
                val errorMessage = """Expected either an ATOM or OPEN token.${'\n'}Actual: ${it.tokenKind}    Value: ${it.value}${'\n'}"""
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