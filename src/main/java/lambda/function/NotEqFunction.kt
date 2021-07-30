package lambda.function

import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.NodeV2
import lambda.core.datamodels.Stack

class NotEqFunction : Function {
    override fun evaluate(
        params: Stack<NodeV2>
    ): NodeV2 {
        val first = params.pop()
        val firstValue = (first as AtomNode).value
        val values = mutableSetOf(firstValue)
        while(params.isNotEmpty()) {
            val node = params.pop()
            val value = (node as AtomNode).value
            if (values.contains(value)) {
                return AtomNode(false)
            }
            values.add(value)
        }
        return AtomNode(true)
    }
}