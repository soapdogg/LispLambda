package lambda.parser.internal

import lambda.core.constants.ReservedValuesConstants
import lambda.core.datamodels.*

class ExpressionListNodeParser {

    fun parseExpressionListNode(
        tokens: List<Token>,
        startingPoint: Int
    ): ParserResult {
        val result = ArrayList<NodeV2>()
        var i = startingPoint
        loop@ while (i < tokens.size) {
            val token = tokens[i]
            when (token.tokenKind) {
                TokenKind.OPEN_TOKEN -> {
                    val nodeV2 = parseExpressionListNode(tokens, i + 1)
                    result += nodeV2.resultingNode
                    i = nodeV2.nextIndex
                }
                TokenKind.CLOSE_TOKEN -> {
                    val nodeV2 = AtomNode(ReservedValuesConstants.NIL)
                    result += nodeV2
                    ++i
                    break@loop
                }
                else -> {
                    val nodeV2 = AtomNode(token.value)
                    result += nodeV2
                    ++i
                }
            }
        }
        val expressionListNode = ExpressionListNode(result)
        return ParserResult(
            expressionListNode,
            i
        )
    }
}