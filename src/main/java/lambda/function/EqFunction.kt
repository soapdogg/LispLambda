package lambda.function

import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.Node

class EqFunction : Function {
    override fun evaluate(
        params: Stack<Node>
    ): Node {
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