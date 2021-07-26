package lambda.parser.internal

import lambda.datamodels.NodeV2
import lambda.datamodels.Token
import lambda.parser.internal.ExpressionListNodeParser

class NodeParser (
  private val expressionListNodeParser: ExpressionListNodeParser
) {

    fun parseIntoNode(
        tokens: List<Token>
    ): NodeV2 {
        return expressionListNodeParser.parseExpressionListNode(tokens, 0).resultingNode.children[0]
    }
}