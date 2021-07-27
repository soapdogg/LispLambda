package lambda.function

import lambda.core.constants.ReservedValuesConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.NodeV2

class IntFunction : Function {

    override fun evaluate(
        params: Stack<NodeV2>
    ): NodeV2 {
        val first = params.pop()
        return if (first is AtomNode) {
            val value = first.value
            val isNumeric = value.matches(Regex(ReservedValuesConstants.NUMERIC_PATTERN))
            AtomNode(isNumeric)
        } else {
            AtomNode(false)
        }
    }
}