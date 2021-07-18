package lambda.function

import lambda.constants.ReservedValuesConstants
import lambda.datamodels.AtomNode
import lambda.datamodels.Stack
import lambda.datamodels.NodeV2
import lambda.generator.NodeGenerator

class NullFunction(
    private val nodeGenerator: NodeGenerator
): Function {

    override fun evaluate(
        params: Stack<NodeV2>
    ): NodeV2 {
        val first = params.pop()
        return if (first is AtomNode) {
            val value = first.value
            val isNil = value == ReservedValuesConstants.NIL
            nodeGenerator.generateAtomNode(isNil)
        } else {
            nodeGenerator.generateAtomNode(false)
        }
    }
}