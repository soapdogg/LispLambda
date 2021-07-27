package lambda.function

import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.NodeV2

class AtomFunction : Function {

    override fun evaluate(
        params: Stack<NodeV2>
    ): NodeV2 {
        val first = params.pop()
        return AtomNode(first is AtomNode)
    }
}