package lambda.function

import lambda.datamodels.AtomNode
import lambda.datamodels.Stack
import lambda.datamodels.NodeV2
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