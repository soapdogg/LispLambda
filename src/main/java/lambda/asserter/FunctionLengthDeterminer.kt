package lambda.asserter

import lambda.core.constants.ReservedValuesConstants
import lambda.core.datamodels.*

class FunctionLengthDeterminer {

    fun determineFunctionLength(node: Node): Int {
        return if (node is AtomNode) {
            if (node.value == ReservedValuesConstants.NIL) 0 else 1
        } else {
            val expressionNode = node as ExpressionListNode
            val lastChild = expressionNode.children[expressionNode.children.size - 1]
            return if (lastChild is AtomNode && lastChild.value == ReservedValuesConstants.NIL) {
                expressionNode.children.size - 1
            } else {
                expressionNode.children.size
            }
        }
    }
}