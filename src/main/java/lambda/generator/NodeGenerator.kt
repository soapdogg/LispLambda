package lambda.generator

import lambda.constants.ReservedValuesConstants
import lambda.datamodels.*

class NodeGenerator {
    fun generateAtomNode(
        value: Boolean
    ): AtomNode {
        val t = if (value) ReservedValuesConstants.T else ReservedValuesConstants.NIL
        return AtomNode(
            t
        )
    }

    fun generateAtomNode(
        value: Int
    ): AtomNode {
        val t = value.toString()
        return AtomNode(
            t
        )
    }

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