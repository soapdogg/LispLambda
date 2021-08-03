package lambda.function

import lambda.core.datamodels.ExpressionListNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.Node

class ConsFunction : Function {
    override fun evaluate(
        params: Stack<Node>
    ): Node {
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