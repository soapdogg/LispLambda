package lambda.function

import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.Node

class AtomFunction : Function {

    override fun evaluate(
        params: Stack<Node>
    ): Node {
        val first = params.pop()
        return AtomNode(first is AtomNode)
    }
}