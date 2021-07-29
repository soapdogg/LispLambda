package lambda.function

import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.NodeV2

class EqFunction : Function {
    override fun evaluate(
        params: Stack<NodeV2>
    ): NodeV2 {
        val first = params.pop()

        while(params.isNotEmpty()) {
            val node = params.pop()
            if (node != first) {
                return AtomNode(false)
            }
        }
        return AtomNode(true)
    }
}