package lambda.printer

import lambda.core.constants.ReservedValuesConstants
import lambda.core.constants.TokenValueConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.ExpressionListNode
import lambda.core.datamodels.Node
import java.lang.StringBuilder

class ListNotationPrinter {

    fun printInListNotation(
        nodes: List<Node>
    ): String {
        return nodes.joinToString(
            ReservedValuesConstants.NEW_LINE.toString(),
            ReservedValuesConstants.EMPTY,
            ReservedValuesConstants.NEW_LINE.toString()
        ) { nodeV2 ->
            printInListNotation(nodeV2)
        }
    }

    fun printInListNotation(node: Node):String {
        if (node is ExpressionListNode) {
            val sb = StringBuilder()
            sb.append(TokenValueConstants.OPEN_PARENTHESES)
            var i = 0
            while (i < node.children.size - 1){
                sb.append(printInListNotation(node.children[i]))
                sb.append(ReservedValuesConstants.SPACE)
                ++i
            }
            var result = sb.toString().trim()
            val lastChild = node.children[node.children.size - 1]
            if (lastChild is AtomNode && lastChild.value == ReservedValuesConstants.NIL) {
                result += TokenValueConstants.CLOSE_PARENTHESES
            } else {
                result += ReservedValuesConstants.LIST_DELIMITER
                result += printInListNotation(lastChild)
                result += TokenValueConstants.CLOSE_PARENTHESES
            }
            return result
        }
        return (node as AtomNode).value
    }
}