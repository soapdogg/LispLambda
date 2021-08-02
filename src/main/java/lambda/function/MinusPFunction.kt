package lambda.function

import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.NodeV2
import lambda.core.datamodels.Stack
import lambda.function.internal.NumericValueRetriever

class MinusPFunction(
    private val numericValueRetriever: NumericValueRetriever
):Function {
    override fun evaluate(
        params: Stack<NodeV2>
    ): NodeV2 {
        val numeric = numericValueRetriever.retrieveNumericValue(
            params.pop(),
            FunctionNameConstants.MINUS_P,
            1
        )
        val isNegative = numeric < 0
        return AtomNode(isNegative)
    }
}