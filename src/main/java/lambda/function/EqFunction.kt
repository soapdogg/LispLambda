package lambda.function

import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.NodeV2

class EqFunction : Function {
    override fun evaluate(
        params: Stack<NodeV2>
    ): NodeV2 {
        val first = params.pop()
        val second = params.pop()
        val isEqual = first == second
        return AtomNode(isEqual)
    }
}