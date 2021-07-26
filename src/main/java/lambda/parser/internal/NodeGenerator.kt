package lambda.parser.internal

import lambda.datamodels.AtomNode
import lambda.datamodels.ExpressionListNode
import lambda.datamodels.NodeV2

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