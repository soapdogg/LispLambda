package lambda.evaluator.rootnode.internal

import lambda.core.datamodels.*

class NodeGenerator {

    fun generateAtomNode(
        value: String
    ): AtomNode {
        return AtomNode(value)
    }

    fun generateExpressionListNode(
        children: List<NodeV2>
    ): ExpressionListNode {
        return ExpressionListNode(
            children
        )
    }
}