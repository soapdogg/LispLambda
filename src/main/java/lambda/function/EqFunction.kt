package lambda.function

import lambda.datamodels.Stack
import lambda.datamodels.NodeV2
import lambda.generator.NodeGenerator

class EqFunction(
    private val nodeGenerator: NodeGenerator
): Function {
    override fun evaluate(
        params: Stack<NodeV2>
    ): NodeV2 {
        val first = params.pop()
        val second = params.pop()
        val isEqual = first == second
        return nodeGenerator.generateAtomNode(isEqual)
    }
}