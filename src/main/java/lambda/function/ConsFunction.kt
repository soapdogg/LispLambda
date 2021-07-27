package lambda.function

import lambda.core.datamodels.ExpressionListNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.NodeV2

class ConsFunction : Function {
    override fun evaluate(
        params: Stack<NodeV2>
    ): NodeV2 {
        val first = params.pop()
        val second = params.pop()

        return if (second is ExpressionListNode) {
            ExpressionListNode(
                listOf(first) + second.children
            )
        } else {
            ExpressionListNode(
                listOf(first, second)
            )
        }
    }
}