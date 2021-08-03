package lambda.parser.internal

import lambda.core.constants.ReservedValuesConstants
import lambda.core.datamodels.*

class NodeParser {

    fun parseIntoNode(
        tokens: List<Token>
    ): NodeV2 {
        if (tokens.size == 1) {
            return AtomNode(tokens[0].value)
        }

        val stack = Stack<ArrayList<NodeV2>>()
        val tokensIterator = tokens.iterator()
        var result = ExpressionListNode(ArrayList())
        while(tokensIterator.hasNext()) {
            val next = tokensIterator.next()
            when(next.tokenKind) {
                TokenKind.OPEN_TOKEN -> {
                    stack.push(ArrayList())
                }
                TokenKind.CLOSE_TOKEN -> {
                    val node = AtomNode(ReservedValuesConstants.NIL)
                    stack.peek().add(node)
                    result = ExpressionListNode(stack.pop())
                    if (stack.isNotEmpty()) {
                        stack.peek().add(result)
                    }
                }
                else -> {
                    val node = AtomNode(next.value)
                    stack.peek().add(node)
                }
            }
        }
        return result
    }
}