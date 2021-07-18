package lambda.function

import lambda.datamodels.ExpressionListNode
import lambda.datamodels.Stack
import lambda.datamodels.NodeV2
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