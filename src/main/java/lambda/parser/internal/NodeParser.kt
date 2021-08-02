package lambda.parser.internal

import lambda.core.datamodels.NodeV2
import lambda.core.datamodels.Token

class NodeParser (
  private val expressionListNodeParser: ExpressionListNodeParser
) {

    fun parseIntoNode(
        tokens: List<Token>
    ): NodeV2 {
        return expressionListNodeParser.parseExpressionListNode(tokens)
    }
}