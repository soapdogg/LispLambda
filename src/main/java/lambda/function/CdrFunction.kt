package lambda.function

import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.Stack
import lambda.core.datamodels.NodeV2
import lambda.function.internal.NodeGenerator
import lambda.function.internal.ListValueRetriever

class CdrFunction(
    private val listValueRetriever: ListValueRetriever,
    private val nodeGenerator: NodeGenerator
): Function {
    override fun evaluate(
        params: Stack<NodeV2>
    ): NodeV2 {
        val first = params.pop()

        val firstExpressionListNode = listValueRetriever.retrieveListValue(
            first,
            FunctionNameConstants.CDR
        )

        return when (firstExpressionListNode.children.size) {
            1 -> {
                firstExpressionListNode.children[0]
            }
            2 -> {
                firstExpressionListNode.children[1]
            }
            else -> {
                nodeGenerator.generateExpressionListNode(
                    firstExpressionListNode.children.subList(1, firstExpressionListNode.children.size)
                )
            }
        }
    }

}