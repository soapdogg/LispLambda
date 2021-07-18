package lambda.printer

import lambda.constants.ReservedValuesConstants
import lambda.datamodels.AtomNode
import lambda.datamodels.ExpressionListNode
import lambda.datamodels.NodeV2

class DotNotationPrinter (
    private val atomNodePrinter: AtomNodePrinter,
    private val dotNotationExpressionNodePrinter: DotNotationExpressionNodePrinter
) {

    fun printInDotNotation(nodes: List<NodeV2>): String {
        if (nodes.isEmpty()) return """${ReservedValuesConstants.NIL}${ReservedValuesConstants.NEW_LINE}"""
        return nodes.joinToString(
            ReservedValuesConstants.NEW_LINE.toString(),
            ReservedValuesConstants.EMPTY,
            ReservedValuesConstants.NEW_LINE.toString()
        ) {node -> printInDotNotation(node)}
    }

    fun printInDotNotation(node: NodeV2): String {
        return if (node is ExpressionListNode) dotNotationExpressionNodePrinter.printExpressionNodeInDotNotation(node)
        else atomNodePrinter.printAtomNode(node as AtomNode)
    }
}