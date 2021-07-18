package lambda.determiner

import lambda.constants.ReservedValuesConstants
import lambda.datamodels.*

class FunctionLengthDeterminer {

    fun determineFunctionLength(node: NodeV2): Int {
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