package lambda.evaluator

import lambda.core.datamodels.*

internal interface NodeGenerator {
    fun generateAtomNode(
        value: String
    ): AtomNode

    fun generateExpressionListNode(
        children: List<Node>
    ): ExpressionListNode
}

internal class NodeGeneratorImpl: NodeGenerator {

    override fun generateAtomNode(
        value: String
    ): AtomNode {
        return AtomNode(value)
    }

    override fun generateExpressionListNode(
        children: List<Node>
    ): ExpressionListNode {
        return ExpressionListNode(
            children
        )
    }
}