package lambda.printer

import lambda.constants.ReservedValuesConstants
import lambda.constants.TokenValueConstants
import lambda.datamodels.AtomNode
import lambda.datamodels.ExpressionListNode
import lambda.datamodels.NodeV2
import java.lang.StringBuilder

class ListNotationPrinter (
    private val atomNodePrinter: AtomNodePrinter
){

    fun printInListNotation(
        nodes: List<NodeV2>
    ): String {
        return nodes.joinToString(
            ReservedValuesConstants.NEW_LINE.toString(),
            ReservedValuesConstants.EMPTY,
            ReservedValuesConstants.NEW_LINE.toString()
        ) { nodeV2 ->
            printInListNotation(nodeV2)
        }
    }

    fun printInListNotation(node: NodeV2):String {
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
        return atomNodePrinter.printAtomNode(node as AtomNode)
    }
}