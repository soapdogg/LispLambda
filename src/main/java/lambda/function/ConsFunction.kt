package lambda.function

import lambda.core.datamodels.ExpressionListNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.NodeV2
import lambda.generator.NodeGenerator

class ConsFunction(
    private val nodeGenerator: NodeGenerator
): Function {
    override fun evaluate(
        params: Stack<NodeV2>
    ): NodeV2 {
        val first = params.pop()
        val second = params.pop()

        return if (second is ExpressionListNode) {
            nodeGenerator.generateExpressionListNode(
                listOf(first) + second.children
            )
        } else {
            nodeGenerator.generateExpressionListNode(
                listOf(first, second)
            )
        }
    }
}