package lambda.function

import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.NodeV2
import lambda.determiner.NumericStringDeterminer
import lambda.generator.NodeGenerator

class IntFunction(
    private val numericStringDeterminer: NumericStringDeterminer,
    private val nodeGenerator: NodeGenerator
): Function {

    override fun evaluate(
        params: Stack<NodeV2>
    ): NodeV2 {
        val first = params.pop()
        return if (first is AtomNode) {
            val value = first.value
            val isNumeric = numericStringDeterminer.isStringNumeric(value)
            nodeGenerator.generateAtomNode(isNumeric)
        } else {
            nodeGenerator.generateAtomNode(false)
        }
    }
}