package lambda.parser.internal

import lambda.core.constants.ReservedValuesConstants
import lambda.core.constants.TokenValueConstants
import lambda.core.datamodels.*

class NodeParser {

    fun parseIntoNode(
        tokens: List<String>
    ): NodeV2 {
        if (tokens.size == 1) {
            return AtomNode(tokens[0])
        }

        val stack = Stack<ArrayList<NodeV2>>()
        val tokensIterator = tokens.iterator()
        var result = ExpressionListNode(ArrayList())
        while(tokensIterator.hasNext()) {
            when(val next = tokensIterator.next()) {
                TokenValueConstants.OPEN_PARENTHESES.toString() -> {
                    stack.push(ArrayList())
                }
                TokenValueConstants.CLOSE_PARENTHESES.toString() -> {
                    val node = AtomNode(ReservedValuesConstants.NIL)
                    stack.peek().add(node)
                    result = ExpressionListNode(stack.pop())
                    if (stack.isNotEmpty()) {
                        stack.peek().add(result)
                    }
                }
                else -> {
                    val node = AtomNode(next)
                    stack.peek().add(node)
                }
            }
        }
        return result
    }
}