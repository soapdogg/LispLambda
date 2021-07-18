package lambda.function

import lambda.datamodels.AtomNode
import lambda.datamodels.Stack
import lambda.datamodels.NodeV2
import lambda.generator.NodeGenerator

class AtomFunction(
    private val nodeGenerator: NodeGenerator
): Function {

    override fun evaluate(
        params: Stack<NodeV2>
    ): NodeV2 {
        val first = params.pop()
        return nodeGenerator.generateAtomNode(first is AtomNode)
    }
}