package lambda.function

import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.NodeV2
import lambda.core.datamodels.Stack
import lambda.function.internal.NumericValueRetriever

class ZeroPFunction(
    private val numericValueRetriever: NumericValueRetriever
): Function {

    override fun evaluate(
        params: Stack<NodeV2>
    ): NodeV2 {
        val numeric = numericValueRetriever.retrieveNumericValue(
            params.pop(),
            FunctionNameConstants.ZERO_P,
            1
        )
        val isZero = numeric == 0
        return AtomNode(isZero)
    }
}